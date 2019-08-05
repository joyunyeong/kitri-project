package com.kitri.Book.rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;



//유저등록할때 받아야하는 값
//유저 ID, 유저 패스워드, 이름 , 권한

public class BookDAO {

   BookDTO dto;
   DBManager dbm;
   
   // DB연결시 필요
   Connection conn = null;
   PreparedStatement ps = null;
   ResultSet rs = null;

   public BookDAO() {

   }
   
   // BookSearch 부분
   public Vector<BookDTO> select(String bookname) {
      Vector<BookDTO> list = new Vector<BookDTO>();
      
      conn = dbm.getConnection();
      
      String select = "SELECT book_name, author, publisher " + "FROM book WHERE book_name like '%" + bookname +"%'";

      try {

         ps = conn.prepareStatement(select);
         rs = ps.executeQuery();

         while (rs.next()) {
            String book_name = rs.getString(1);
            String author = rs.getString(2);
            String publisher = rs.getString(3);
//            String possible = rs.getString(4);

            dto = new BookDTO();

            dto.setBookName(book_name);
            dto.setAuthor(author);
            dto.setPublisher(publisher);
//            dto.setRentState(possible);

            list.add(dto);
         }
      } catch (SQLException e1) {
         System.out.println("테이블 출력 오류");
         e1.printStackTrace();
      } finally {
         try {
            dbm.dbClose(ps, conn);
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return list;
   }


   public Vector<BookDTO> BookList() {
      // 보여지는 테이블에 넣는 값들 : 유저코드, 패스워드, 아이디, 권한, 이름
      Vector<BookDTO> list = new Vector<BookDTO>();
      conn = dbm.getConnection();
      String select = "select book_name, author, publisher " + "from book";
      try {
         ps = conn.prepareStatement(select);
         rs = ps.executeQuery();
         while (rs.next()) {
            String book_name = rs.getString(1);
            String author = rs.getString(2);
            String publisher = rs.getString(3);
//            if(rs.getString("rent_state").equals("0"))
               
            dto = new BookDTO();
            dto.setBookName(book_name);
            dto.setAuthor(author);
            dto.setPublisher(publisher);
//            dto.setRentState(possible);
            list.add(dto);
//            row.clear();
         }

      } catch (SQLException e1) {
         System.out.println("테이블 출력 오류");
         e1.printStackTrace();
      } finally {
         try {
            dbm.dbClose(ps, conn);
         } catch (SQLException e) {

            e.printStackTrace();
         }
      }
      return list;

   }

	public Vector<BookDTO> move(String list) {
		Vector<BookDTO> basket = new Vector<BookDTO>();
		conn = dbm.getConnection();
		String select = "SELECT rownum, book_ctg_name, book_name, book_price*0.1, 1\r\n" + "FROM category c, book b\r\n"
				+ "WHERE b.book_ctg = c.book_ctg AND book_name = '" + list + "'";
	
		try {
			ps = conn.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt(1);
				String book_ctg_name = rs.getString(2);
				String book_name = rs.getString(3);
				int book_price = rs.getInt(4);
				int one = rs.getInt(5);
	
				dto = new BookDTO();
	
				dto.setRowNum(rownum);
				dto.setBook_ctg_name(book_ctg_name);
				dto.setBookName(book_name);
				dto.setBookPrice(book_price);
				dto.setOne(one);
	
				basket.add(dto);
			}
		} catch (SQLException e1) {
			System.out.println("테이블 출력 오류");
			e1.printStackTrace();
		} finally {
			try {
				dbm.dbClose(ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return basket;
		
	}

	public BookDTO selectshbook(String bookname) {
	      Vector<BookDTO> list = new Vector<BookDTO>();
	      
	      conn = dbm.getConnection();
	      
	      String select = "SELECT * " + "FROM book WHERE book_name like '%" + bookname +"%'";

	      try {

	         ps = conn.prepareStatement(select);
	         rs = ps.executeQuery();

	         while (rs.next()) {
	        	String book_num = rs.getString(1).substring(0,10);
	            String book_name = rs.getString(3);
	            String author = rs.getString(5);
	            String publisher = rs.getString(6);
	            String bookPrice = rs.getString(7);

	            dto = new BookDTO();

	            dto.setBookName(book_name);
	            dto.setAuthor(author);
	            dto.setPublisher(publisher);
	            dto.setBookPrice(Integer.parseInt(bookPrice));
	            dto.setBookNum(book_num);
	            list.add(dto);
	         }
	      } catch (SQLException e1) {
	         System.out.println("테이블 출력 오류");
	         e1.printStackTrace();
	      } finally {
	         try {
	            dbm.dbClose(ps, conn);
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return dto;
	   }



}