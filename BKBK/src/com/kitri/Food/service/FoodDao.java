package com.kitri.Food.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.kitri.Food.data.FoodDto;
import com.kitri.Food.data.OrderHstrDto;
import com.kitri.Food.data.StockDto;

public class FoodDao {

	private Connection conn;
	private ResultSet rs;

	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "project1";
	String pw = "project1";

	////////////////////////////////
	private static FoodDao instance = new FoodDao(); // 싱글톤 : 자기자신 객체 생성

	private FoodDao() {
	}

	public static FoodDao getInstance() {//외부에서 클래스명. getInstance()를 호출하면 이 클래스의 객체가 반환됨
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);//JDBC 드라이버 로딩 : ojdbc.jar파일에 있는 OracleDriver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	//한개의 food 정보
	public FoodDto selectFood(String food_name) {
		FoodDto foodDto = new FoodDto(); // FoodDto에 OrderHstrDto, StockDto 생성해놓음
		
		conn = null;					//연결
		PreparedStatement stmt = null;	//명령
		rs = null;						//결과
		
		
		try {
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "select * "
					+ "from food "
					+ "where food.food_name = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, food_name);//?의 숫자번쨰
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				foodDto.setFoodCtg(rs.getString("food_ctg"));
				foodDto.setFoodName(rs.getString("food_name"));
				foodDto.setFoodPrice(rs.getInt("food_price"));
				foodDto.setStock1Name(rs.getString("stock1_name"));
				foodDto.setStock1Num(rs.getInt("stock1_num"));
				foodDto.setStock2Name(rs.getString("stock2_name"));
				foodDto.setStock2Num(rs.getInt("stock2_num"));
				foodDto.setStock3Name(rs.getString("stock3_name"));
				foodDto.setStock3Num(rs.getInt("stock3_num"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("연결 실패" + e.getStackTrace());
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("해제 실패" + e.getStackTrace());
			}
		}
		return foodDto;
	}
	
	//food_name 리스트 출력 (카테고리별)
		public List<String> getFoodnamelist(String foodctg) {
			List<String> list = new ArrayList<String>();
			
			conn = null;					//연결
			PreparedStatement stmt = null;	//명령
			rs = null;	
			
			try {
				conn = DriverManager.getConnection(url, user, pw);
				
				String sql= "select food_name from food "
							+ "where food_ctg = ?";
						
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, foodctg);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					String food_name = rs.getString("food_name");
					list.add(food_name);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)
						rs.close();
					if(stmt != null)
						stmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					System.out.println("해제 실패" + e.getStackTrace());
				}
			}
			return list ;
		}
//	//Order정보 insert하기
//		public void insertOrder(String food_name, String member_id,String pay_num,int order_amt) {
//			
//			conn = null;					//연결
//			PreparedStatement stmt = null;	//명령
//			rs = null;	
//			
//			try {
//				conn = DriverManager.getConnection(url, user, pw);
//				
//				String sql= "insert into order_hstr(food_name,member_id,pay_num,order_amt,pay_date) "
//						+ "values(?,?,?,?,?)";
//						
//				stmt = conn.prepareStatement(sql);
//				stmt.setString(1, food_name);
//				stmt.setString(2, member_id);
//				stmt.setString(3, pay_num);
//				stmt.setInt(4, order_amt);
//				stmt.setString(5, "");//////////////////////
//				rs = stmt.executeQuery();
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if(rs != null)
//						rs.close();
//					if(stmt != null)
//						stmt.close();
//					if(conn != null)
//						conn.close();
//				} catch (SQLException e) {
//					System.out.println("해제 실패" + e.getStackTrace());
//				}
//			}
//		}
		
	
		
		//재고 찾기 이름으로
		public StockDto getStock(String stockname) {
			StockDto stockDto = new StockDto();
			
			conn = null;					//연결
			PreparedStatement stmt = null;	//명령
			rs = null;	
			
			try {
				conn = DriverManager.getConnection(url, user, pw);
				
				String sql= "select stock_name, rest_amt from stock "
							+ "where stock_name = ?";
						
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, stockname);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					stockDto.setStockName(rs.getString("stock_name"));
					stockDto.setRestAmt(rs.getInt("rest_amt"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)
						rs.close();
					if(stmt != null)
						stmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					System.out.println("해제 실패" + e.getStackTrace());
				}
			}
			return stockDto ;
		}
		
		//재고 빼기
		public void updateStock(int restamt, String stockname) {
			
			
			conn = null;					//연결
			PreparedStatement stmt = null;	//명령
			rs = null;	
			
			try {
				conn = DriverManager.getConnection(url, user, pw);
				
				String sql= "update stock set rest_amt = ?"
							+ "where stock_name = ?";
						
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, restamt);
				stmt.setString(2, stockname);
				
				rs = stmt.executeQuery();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)
						rs.close();
					if(stmt != null)
						stmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					System.out.println("해제 실패" + e.getStackTrace());
				}
			}
			
		}
}