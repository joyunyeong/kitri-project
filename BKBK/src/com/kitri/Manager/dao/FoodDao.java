package com.kitri.Manager.dao;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.kitri.Manager.data.FoodDto;
import com.kitri.Manager.data.StockDto;

public class FoodDao {
	
	//field
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private String sql;
	
	private FoodDto fDto;
	private StockDto sDto;
	private List<String> listL;
	private Vector listV;
	
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "project1";
	String pw = "project1";
	
	
	// 싱글톤 
	private static FoodDao instance = new FoodDao();//자신의 객체를  생성
	private FoodDao() {}
	public static FoodDao getInstance() {//외부에서 클래스명.getInstance()를 호출하면 이 클래스의 객체가 반환됨
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------[h] 관리번호수정
	
		public int upMgmtNum(int mgmtNum) {
			conn = null;
			pstmt = null;
			int result = 0;
			sql = "update manager set manager_id = ?";
			try {
				conn = DriverManager.getConnection(url, user, pw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mgmtNum);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
					try {
						if(rs!=null)
							rs.close();
						if(pstmt!=null)
							pstmt.close();
						if(conn!=null)
							conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return result;
		}
		
//-------------------------------------------------------------------------------------------------------------------------------------------------[h]
		
		
	
//--------------------------------------------------------------------------------------------------------------------------------------------메뉴
	
	
	//카테고리별 메뉴 전체 select
	public List<String> selCtgMenu(String ctg) {
		conn = null;
		pstmt = null;
		sql = "select food_name from food where food_ctg = ? and state = '1' order by food_name";
		listL = new ArrayList<String>();
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ctg);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				listL.add(rs.getString("food_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return listL;
	}
	
	
	public FoodDto findMenu(String name , String ctg) { //이름으로 메뉴 찾기
		fDto = null;
		conn = null;
		pstmt = null;
		sql = "select * from food where food_name = ?";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			rs.next();
			
			fDto = new FoodDto();
			fDto.setFoodCtg(rs.getString("food_ctg"));
			fDto.setFoodName(rs.getString("food_name"));
			fDto.setFoodPrice(rs.getInt("food_price"));
			fDto.setStock1Name(rs.getString("stock1_name"));
			fDto.setStock1Num(rs.getInt("stock1_num"));
			fDto.setStock2Name(rs.getString("stock2_name"));
			fDto.setStock2Num(rs.getInt("stock2_num"));
			fDto.setStock3Name(rs.getString("stock3_name"));
			fDto.setStock3Num(rs.getInt("stock3_num"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return fDto;
	}
	
	
	
	
	
	public int mergeMenu(FoodDto foodDto) {//메뉴 추가&수정
		FoodDto f = foodDto;
		int i = 0;
		conn = null;
		pstmt = null;
		sql = "merge into food" + 
				" using dual" + 
				" on (food_name = ?)" + 
				" when matched then" + 
				" update set food_ctg = ?, food_price = ?, stock1_name = ?, stock1_num = ?," +
				" stock2_name = ?, stock2_num = ?, stock3_name = ?, stock3_num = ?, state = '1'" + 
				" when not matched then" + 
				" insert values (?, ?, ?, ?, ?, ?, ?, ?, ?, '1')";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getFoodName());
			pstmt.setString(2, f.getFoodCtg());//일치하는 이름 있는 경우 update
			pstmt.setInt(3, f.getFoodPrice());
			pstmt.setString(4, f.getStock1Name());
			pstmt.setInt(5, f.getStock1Num());
			
			if(f.getStock2Name()!=null) {
				pstmt.setString(6, f.getStock2Name());
				pstmt.setInt(7, f.getStock2Num());
			} else {
				pstmt.setString(6, null);
				pstmt.setString(7, null);
			}
			if(f.getStock3Name()!=null) {
				pstmt.setString(8, f.getStock3Name());
				pstmt.setInt(9, f.getStock3Num());
			} else {
				pstmt.setString(8, null);
				pstmt.setString(9, null);
			}
			pstmt.setString(10, f.getFoodName());
			pstmt.setString(11, f.getFoodCtg());
			pstmt.setInt(12, f.getFoodPrice());
			pstmt.setString(13, f.getStock1Name());
			pstmt.setInt(14, f.getStock1Num());
			if(f.getStock2Name()!=null) {
				pstmt.setString(15, f.getStock2Name());
				pstmt.setInt(16, f.getStock2Num());
			} else {
				pstmt.setString(15, null);
				pstmt.setString(16, null);
			}
			if(f.getStock3Name()!=null) {
				pstmt.setString(17, f.getStock3Name());
				pstmt.setInt(18, f.getStock3Num());
			} else {
				pstmt.setString(17, null);
				pstmt.setString(18, null);
			}
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return i;
	}
	
	public int delMenu(String foodName, String ctg) {//메뉴삭제
		conn = null;
		pstmt = null;
		sql = "update food set state = '0' where food_ctg = ? and food_name = ?";
		int i = 0;
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ctg);
			pstmt.setString(2, foodName);
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return i;
	}
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------재고
	
	//카테고리별 재고 전체
		public Vector<StockDto> selStock(String ctg) {
			conn = null;
			pstmt = null;
			listV = new Vector();
			try {
				if(!ctg.equals("all")) {
					sql = "select * from stock where food_ctg = ? order by stock_name";
					conn = DriverManager.getConnection(url, user, pw);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, ctg);
				} else {
					sql = "select * from stock order by stock_name";
					conn = DriverManager.getConnection(url, user, pw);
					pstmt = conn.prepareStatement(sql);
				}
				rs = pstmt.executeQuery();
				int i = 0;
				while(rs.next()) {
					Vector a = new Vector();
					a.add(++i);
					a.add(rs.getString("food_ctg"));
					a.add(rs.getString("stock_name"));
					a.add(rs.getInt("rest_amt"));
					a.add(rs.getInt("unit_cost"));
					String a6;
					if(rs.getInt("rest_amt")==0)
						a.add("재고 없음");
					else
						a.add("");

					listV.add(a);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
					try {
						if(rs!=null)
							rs.close();
						if(pstmt!=null)
							pstmt.close();
						if(conn!=null)
							conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return listV;
		}
		
	public List<String> selStockName(String ctg) {//재고 목록(이름) select
		List<String> list = new ArrayList<String>();
		conn = null;
		pstmt = null;
		sql = "select stock_name from stock where food_ctg = ? order by stock_name";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ctg);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("stock_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	
	
	
	public Vector findStockV(String sName) {//품목 검색
		Vector list = new Vector();
		conn = null;
		pstmt = null;
		sql = "select * from stock where stock_name like ?";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ("%"+sName+"%"));
			rs = pstmt.executeQuery();
			int i = 0;
			
			while(rs.next()) {
				Vector a = new Vector();
				a.add(++i);
				a.add(rs.getString("food_ctg"));
				a.add(rs.getString("stock_name"));
				a.add(rs.getInt("rest_amt"));
				a.add(rs.getInt("unit_cost"));
				String a6;
				if(rs.getInt("rest_amt")==0)
					a.add("재고 없음");
				else
					a.add("");

				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	public StockDto findStockD(String sName) {//품목 검색
		StockDto stockDto = new StockDto();
		conn = null;
		pstmt = null;
		sql = "select * from stock where stock_name like ?";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			rs.next();
			stockDto.setFoodCtg(rs.getString("food_ctg"));
			stockDto.setStockName(rs.getString("stock_name"));
			stockDto.setRestAmt(rs.getInt("rest_amt"));
			stockDto.setUnitCost(rs.getInt("unit_cost"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stockDto;
	}
	
	public int mergeStock(StockDto stockDto) {//재고 merge
		StockDto s = stockDto;
		conn = null;
		pstmt = null;
		int i = 0;
		sql = "merge into stock" + 
				" using dual" + 
				" on (stock_name = ?)" + 
				" when matched then" + 
				" update set food_ctg = ?, rest_amt = rest_amt + ?, unit_cost = ?" +
				" when not matched then" + 
				" insert values (?, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getStockName());
			pstmt.setString(2, s.getFoodCtg());//일치하는 이름 있는 경우 update
			pstmt.setInt(3, s.getRestAmt());
			pstmt.setInt(4, s.getUnitCost());
			pstmt.setString(5, s.getFoodCtg());//없는 경우 insert
			pstmt.setString(6, s.getStockName());
			pstmt.setInt(7, s.getRestAmt());
			pstmt.setInt(8, s.getUnitCost());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return i;
	}
	
	public void delStock(String sName) {
		conn = null;
		pstmt = null;
		sql = "delete stock where stock_name = ?";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
}
