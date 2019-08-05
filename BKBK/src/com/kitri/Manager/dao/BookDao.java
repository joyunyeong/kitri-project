package com.kitri.Manager.dao;

import java.sql.*;
import java.util.*;

import com.kitri.Manager.data.*;

public class BookDao {
	private Connection conn;
	private ResultSet rs;
	private FoodDto fDto;
	private StockDto sDto;
	private Statement stmt;
	private PreparedStatement pstmt;
	private String sql;
	
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "project1";
	String pw = "project1";
	
	// 싱글톤 
	private static BookDao instance = new BookDao();//자신의 객체를  생성
	private BookDao() {}
	public static BookDao getInstance() {//외부에서 클래스명.getInstance()를 호출하면 이 클래스의 객체가 반환됨
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public Vector serchB(String colName, String str) {//도서검색
		Vector list = new Vector();
		conn = null;
		pstmt = null;
		try {
			if(colName != null && str != null) {
				sql = "select book_num, genre_name, book_name, book_ctg_name, author, publisher, book_price, rent_state, replace" + 
						" from book b, category c, genre g" + 
						" where b.book_ctg = c.book_ctg and b.genre_num = g.genre_num and " + colName +" like ? and state = '1'" +
						" order by book_name";
				conn = DriverManager.getConnection(url, user, pw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ("%"+str+"%"));
			} else {
				sql = "select book_num, genre_name, book_name, book_ctg_name, author, publisher, book_price, rent_state, replace" + 
						" from book b, category c, genre g" + 
						" where b.book_ctg = c.book_ctg and b.genre_num = g.genre_num and state = '1' order by book_name";
				conn = DriverManager.getConnection(url, user, pw);
				pstmt = conn.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				Vector a = new Vector();
				a.add(++i);
				a.add(rs.getString("book_ctg_name"));
				a.add(rs.getString("genre_name"));
				a.add(rs.getString("book_name"));
				a.add(rs.getString("author"));
				a.add(rs.getString("publisher"));
				a.add(rs.getInt("book_price"));
				if(rs.getString("rent_state").toString().equals("0"))
					a.add("");
				else
					a.add("대출중");
				if(rs.getString("replace").toString().equals("0"))
					a.add("");
				else
					a.add("교체요망");

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
	
	public List<String> selCol(String col1, String col2, String table) {
		List<String> list = new ArrayList<String>();
		conn = null;
		pstmt = null;
		sql = "select " + col1 + " from " + table + " order by " + col2;
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(col1));
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
	
	public BookDto findB(String bName) {
		conn = null;
		pstmt = null;
		sql = "select book_num, genre_name, book_name, book_ctg_name, author, publisher, book_price, rent_state, replace" + 
				" from book b, category c, genre g" + 
				" where b.book_ctg = c.book_ctg and b.genre_num = g.genre_num and book_name = ? and state = '1'";
		BookDto bookDto = new BookDto();
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			rs = pstmt.executeQuery();
			rs.next();
			bookDto.ctgDto.setBookCtgName(rs.getString("book_ctg_name"));
			bookDto.genDto.setGenreName(rs.getString("genre_name"));
			bookDto.setBookName(rs.getString("book_name"));
			bookDto.setAuthor(rs.getString("author"));
			bookDto.setPublisher(rs.getString("publisher"));
			bookDto.setBookPrice(rs.getInt("book_price"));
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
		return bookDto;
	}
	
	public void delBook(String bName) {
		conn = null;
		pstmt = null;
		sql = "update book set state = '0' where book_name = ?";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
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
	
	public int replace(String bName) {
		conn = null;
		pstmt = null;
		int result = 0;
		sql = "update book set replace = '0' where book_name = ?";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
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
	
	public void mergeB(BookDto bookDto) {
		BookDto b = bookDto;
		conn = null;
		pstmt = null;
		
		sql = "merge into book" + 
				" using dual" + 
				" on (book_name = ?)" + 
				" when matched then" + 
				" update set genre_num = ?, book_ctg = ?, author = ?, publisher = ?, book_price = ?, state = '1'" + 
				" when not matched then" + 
				" insert values (sysdate, ?, ?, ?, ?, ?, ?, '0', '0', '1')";
		try {
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBookName());
			pstmt.setInt(2, b.getGenreNum());//일치하는 이름 있는 경우 update
			pstmt.setInt(3, b.getBookCtg());
			pstmt.setString(4, b.getAuthor());
			pstmt.setString(5, b.getPublisher());
			pstmt.setInt(6, b.getBookPrice());
			pstmt.setInt(7, b.getGenreNum());
			pstmt.setString(8, b.getBookName());
			pstmt.setInt(9, b.getBookCtg());
			pstmt.setString(10, b.getAuthor());
			pstmt.setString(11, b.getPublisher());
			pstmt.setInt(12, b.getBookPrice());
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
