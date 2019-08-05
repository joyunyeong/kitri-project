package com.kitri.Statistics.chart;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kitri.Statistics.StatBookDto;
import com.kitri.Statistics.StatFoodDto;
import com.kitri.Statistics.StatMemberDto;

//**************************************************************


public class StatDao {


	
	// DB연결된 상태(세션)을 담은 객체
	Connection conn = null;

	// 쿼리문에 사용하는 state객체
	PreparedStatement ps = null;

	Statement st = null;
	ResultSet rs = null;

	// 멤버 통계 쿼리문 결과 (1행) 담을 StatMemberDto 객체
	StatMemberDto statMemberDto = null;
	StatFoodDto statFoodDto = null;
	StatBookDto statBookDto = null;
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//1. 나이대 통계 SELECT
	public Vector<StatMemberDto> selectMemberRanking() {
		
		// 쿼리문 결과 (여러 행) 담을 StatMemberDto 객체
				Vector<StatMemberDto> list = new Vector<StatMemberDto>();

				try {
					// DB 연결
					conn = getConnection();

					// 쿼리문 세팅
					String query = "select member_id, name, to_char(birth, 'yyyy-mm-dd'),\r\n" + 
							"    case\r\n" + 
							"        when birth < '1979-01-01'\r\n" + 
							"        then '40대이상'\r\n" + 
							"        when birth < '1989-01-01'\r\n" + 
							"        then '30대'\r\n" + 
							"        when birth < '1999-01-01'\r\n" + 
							"        then '20대'\r\n" + 
							"        when birth < '2009-01-01'\r\n" + 
							"        then '10대'\r\n" + 
							"        else '어린이'\r\n" + 
							"    end 연령구분\r\n" + 
							"from member\r\n" + 
							"where state = '1' " +
							"order by birth asc";
					ps = conn.prepareStatement(query);

					// *? 설정
//					ps.setInt(1, date);
//					ps.setString(2, minor_level);

					// 쿼리문 실행
					// select 실행할 때, 조회된 모든 행(ResultSet)을 리턴
					rs = ps.executeQuery();
					
					// *insert, delete, update실행할때, 쿼리문에 의해 변경된 행 개수(int) 리턴
//					rs = ps.executeUpdate();

					// 결과 저장
					while (rs.next()) {
						statMemberDto = new StatMemberDto();
						
						statMemberDto.setMemberId(rs.getString(1));
						statMemberDto.setName(rs.getString(2));
						statMemberDto.setBirth(rs.getString(3));
						statMemberDto.setAge(rs.getString(4));
												

						list.add(statMemberDto);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						dbClose(rs, ps, conn);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				// 결과 리턴
				return list;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	//1. 나이대 그래프만들기 ChartMember
	public Vector<StatMemberDto> rankingGraph() {
		// 쿼리문 결과 (여러 행) 담을 StatMemberDto 객체
		Vector<StatMemberDto> list = new Vector<StatMemberDto>();

		try {
			// DB 연결
			conn = getConnection();

			// 쿼리문 세팅
			String query = "select a.연령구분, count(*)                       \r\n" + 
					"from (select member_id, name, birth,case\r\n" + 
					"                                    when birth < '79/01/01'\r\n" + 
					"                                        then '40대 이상'\r\n" + 
					"                                    when birth < '89/01/01'\r\n" + 
					"                                        then '30대'\r\n" + 
					"                                    when birth < '99/01/01'\r\n" + 
					"                                        then '20대'\r\n" + 
					"							        when birth < '09/01/01' \r\n" + 
					"							            then '10대'\r\n" + 
					"							       else '어린이'\r\n" + 
					"							            end 연령구분\r\n" + 
					"							from member \r\n" +
					"                           where state = '1' " +
					"							order by birth asc) a\r\n" + 
					"group by a.연령구분 ";
			ps = conn.prepareStatement(query);

			// 쿼리문 실행
			// select 실행할 때, 조회된 모든 행(ResultSet)을 리턴
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				statMemberDto = new StatMemberDto();
				
				statMemberDto.setCustomerAge(rs.getString(1));
				statMemberDto.setCustomerAgeCount(rs.getInt(2));
										

				list.add(statMemberDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;
		
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//2. 상품별 매출내역 BEST 5
		public Vector selectFoodRanking(String startdate, String lastdate) {

			// 쿼리문 결과 (여러 행) 담을 PosDto 객체
			Vector list2 = new Vector();


			// String으로 입력한 날짜를 '년월' 합쳐서 int로 변환
			//int date = Integer.parseInt(year.concat(month).concat("01"));

			try {

				// DB 연결
				conn = getConnection();

				// 쿼리문 세팅
				String query = "select row_number()over(order by f.food_price * v.sc desc) as 매출순위, " +
						"f.food_ctg as 상품종류 , f.food_name as 상품명, " + 
						"f.food_price as 상품가격, v.sc as 판매수량, f.food_price * v.sc as 매출합계 " + 
						"from food f, (select food_name, sum(order_amt)sc " + 
						"from order_hstr " + 
						"where substr(pay_num, 1,6) between ? and ? " + 
						"group by food_name)v " + 
						"where f.food_name = v.food_name ";

				ps = conn.prepareStatement(query);
				ps.setString(1, startdate);
				ps.setString(2, lastdate);

				// 쿼리문 실행
				rs = ps.executeQuery();

				// 결과 저장
				while (rs.next()) {

					Vector list = new Vector();
					list.add(rs.getInt(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
					list.add(rs.getInt(4));
					list.add(rs.getInt(5));
					list.add(rs.getInt(6));
					
					list2.add(list);
					
				}



			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

				try {

					// DB 연결 종료
					dbClose(rs, ps, conn);

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// 결과 리턴

			return list2;

		}
	
		/////////////////////////////////////////////////////////////////////////////////////////
		//2. 상품별 매출합계 그래프 만들기
		public Vector<StatFoodDto> productTotalGraph(String startdate, String lastdate) {
			// 쿼리문 결과 (여러 행) 담을 StatMemberDto 객체
			Vector<StatFoodDto> list = new Vector<StatFoodDto>();

			try {
				// DB 연결
				conn = getConnection();

				// 쿼리문 세팅
				String query = "select f.food_name as 상품명, f.food_price * v.sc as 매출합계  \r\n" + 
						"from food f, (select food_name, sum(order_amt)sc  \r\n" + 
						"                from order_hstr  \r\n" + 
						"                where substr(pay_num, 1,6) between ? and ? \r\n" + 
						"                group by food_name)v \r\n" + 
						"where f.food_name = v.food_name";
				ps = conn.prepareStatement(query);

				// 쿼리문 실행
				// select 실행할 때, 조회된 모든 행(ResultSet)을 리턴
				ps.setInt(1, Integer.parseInt(startdate));
				ps.setInt(2, Integer.parseInt(lastdate));
				rs = ps.executeQuery();

				// 결과 저장
				while (rs.next()) {
					statFoodDto = new StatFoodDto();
					
					statFoodDto.setProductName(rs.getString(1));
					statFoodDto.setTotalSellPrice(rs.getInt(2));
											

					list.add(statFoodDto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					dbClose(rs, ps, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// 결과 리턴
			return list;
			
		}
//		
//		////////////////////////////////////////////////////////////////////////////
//		//3. 도서별 인기순위
//				public Vector selectBookRanking(String startdate, String lastdate) {
//
//					// 쿼리문 결과 (여러 행) 담을 PosDto 객체
//					Vector list2 = new Vector();
//
//
//					// String으로 입력한 날짜를 '년월' 합쳐서 int로 변환
//					//int date = Integer.parseInt(year.concat(month).concat("01"));
//
//					try {
//
//						// DB 연결
//						conn = getConnection();
//
//						// 쿼리문 세팅
//						String query = "select row_number()over(order by v.sc desc) as 매출순위, \r\n" + 
//								"       b.book_name as 도서이름, g.genre_name as 도서장르, \r\n" + 
//								"        c.book_ctg_name as 도서종류\r\n" + 
//								"from book b, genre g, category c,(select book_num, count(pay_num)sc \r\n" + 
//								"                                                 from rent_hstr\r\n" + 
//								"                                                 where substr(pay_num, 1,6) between ? and ? \r\n" + 
//								"                                                 group by book_num)v \r\n" + 
//								"where  b.book_num = v.book_num\r\n" + 
//								"    and b.genre_num = g.genre_num\r\n" + 
//								"    and b.book_ctg = c.book_ctg";
//
//						ps = conn.prepareStatement(query);
//						ps.setString(1, startdate);
//						ps.setString(2, lastdate);
//
//						// 쿼리문 실행
//						rs = ps.executeQuery();
//
//						// 결과 저장
//						while (rs.next()) {
//
//							Vector list = new Vector();
//							list.add(rs.getInt(1));
//							list.add(rs.getString(2));
//							list.add(rs.getString(3));
//							list.add(rs.getString(4));
//				
//							
//							list2.add(list);
//							
//						}
//
//
//
//					} catch (SQLException e) {
//
//						e.printStackTrace();
//
//					} finally {
//
//						try {
//
//							// DB 연결 종료
//							dbClose(rs, ps, conn);
//
//						} catch (SQLException e) {
//
//							e.printStackTrace();
//						}
//					}
//
//					// 결과 리턴
//
//					return list2;
//
//				}		
//		
				
				
				/////////////////////////////////////////////////////////////////////////////////////////
				//3. 도서별 인기순위 그래프 만들기
				public Vector<StatBookDto> BookTotalGraph(String startdate, String lastdate) {
					// 쿼리문 결과 (여러 행) 담을 StatMemberDto 객체
					Vector<StatBookDto> list = new Vector<StatBookDto>();

					try {
						// DB 연결
						conn = getConnection();

						// 쿼리문 세팅
						String query = "select v.sc as 대출개수,b.book_name as 도서이름\r\n" + 
								"from book b, genre g, category c,(select book_num, count(pay_num)sc \r\n" + 
								"                                                 from rent_hstr\r\n" + 
								"                                                 where substr(pay_num, 1,6) between ? and ?\r\n" + 
								"                                                 group by book_num)v \r\n" + 
								"where  b.book_num = v.book_num\r\n" + 
								"    and b.genre_num = g.genre_num\r\n" + 
								"    and b.book_ctg = c.book_ctg";
						ps = conn.prepareStatement(query);

						// 쿼리문 실행
						// select 실행할 때, 조회된 모든 행(ResultSet)을 리턴
						ps.setInt(1, Integer.parseInt(startdate));
						ps.setInt(2, Integer.parseInt(lastdate));
						rs = ps.executeQuery();

						// 결과 저장
						while (rs.next()) {
							statBookDto = new StatBookDto();
							
							statBookDto.setTotalcount(rs.getInt(1));
							statBookDto.setBookName(rs.getString(2));
													
							list.add(statBookDto);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							dbClose(rs, ps, conn);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					// 결과 리턴
					return list;
					
				}				
				
				
	

	/////////////////////////////////////////////////// [DB 연결 및 해제 메소드]/////////////////////////////////////////////////// ////////////////////////////////////////////////
	// <DB 연결> 메소드 : 연결된 DB 상태를 리턴함
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String user = "project1";
			String pw = "project1";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		return conn;
	}

	// *rs : ResultSet
	// st : Statement
	// ps : PreparedStatement
	// conn : Connection

	// <DB 연결 해제 1> 메소드 : ps + conn
	public static void dbClose(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}

	// <DB 연결 해제 2> 메소드 : st + conn
	public static void dbClose(Statement st, Connection conn) throws SQLException {
		if (st != null)
			st.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}

	// <DB 연결 해제 3> 메소드 : rs + ps + conn
	public static void dbClose(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}

	// <DB 연결 해제 4> 메소드 : rs + st + conn
	public static void dbClose(ResultSet rs, Statement st, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}




}
