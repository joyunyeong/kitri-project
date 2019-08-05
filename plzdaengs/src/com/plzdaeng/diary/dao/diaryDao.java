package com.plzdaeng.diary.dao;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.plzdaeng.dto.DiaryDto;
import com.plzdaeng.dto.DiaryImgDto;
import com.plzdaeng.dto.UserDetailDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;

/*
 * 1-3. 다이어리 등록/수정/삭제
 * 4-6. 이모티콘 등록/수정/삭제
 */
public class diaryDao {
	
	//다이어리 인설트하고 현재 넣은 다이어리 key값을 가져오는 부분
	public int selectDiaryNumber(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		String selectDiaryNumberSQL = 
				"select \r\n" + 
				"    SEQ_PLZ_DIARY.currval as diary_number\r\n" + 
				"from dual";
		
		pstmt = conn.prepareStatement(selectDiaryNumberSQL);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			result = rs.getInt("diary_number");
		}
		
		DBClose.close(null, pstmt, rs);
		
		return result;
	}
	
	//다이어리에 일정 등록하는 부분
	//return : insert 된 다이어리 객체의 DB상 KEY값
	// 이 값을 통해 다시 조회할 때 사용
	public int insertDiary(UserDto user, DiaryDto dto) {
		/*
		 * insert into plz_diary(diary_number, user_id, diary_date, diary_subject, diary_contents, diary_img, create_Date)
		 * VALUES (SEQ_PLZ_DIARY.nextval,'meme',sysdate, '오늘 댕댕이랑','댕이이','5.jpg',sysdate);
		 */
		System.out.println("> 다이어리 생성 관련 DB 접근 성공!");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String insertDiarySQL = 
				"insert into plz_diary(	\r\n" + 
				"    diary_number	\r\n" + 
				"    , user_id	\r\n" + 
				"    , category_id	\r\n" + 
				"    , diary_subject	\r\n" + 
				"    , diary_contents	\r\n" + 
				"	 , diary_date		\r\n" +
				"    , diary_img	\r\n" + 
				"    , create_date	\r\n" + 
				")values(	\r\n" + 
				"    SEQ_PLZ_DIARY.nextval	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , sysdate\r\n" + 
				")";
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(insertDiarySQL);
			int index = 0;
			//dto.setUser_id("mnmm97");
			dto.setUser_id(user.getUser_id());
			Date date = new Date(dto.getDiary_date().getTime()); 
			System.out.println(dto);
			
			pstmt.setString(++index, dto.getUser_id());
			pstmt.setString(++index, dto.getCategory_id());
			pstmt.setString(++index, dto.getDiary_subject());
			pstmt.setString(++index, dto.getDiary_contents());
			pstmt.setDate(++index, date);
			pstmt.setString(++index, dto.getDiary_img());
			
			pstmt.executeUpdate();
			result = selectDiaryNumber(conn);
			
			conn.commit();
			System.out.println("	> [성공] DTO결과 불러오기 : " + dto);
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, null);
			System.out.println("> DB를 종료합니다.");
		}
		
		return result;							
	}
	
	// 월이 바뀌면 호출되는 메소드
	// yyyy/mm 형태로 온다고 생각하겠음
	public List<DiaryDto> selectAllByMonth(String month, UserDto user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DiaryDto> list = new ArrayList<DiaryDto>();
		String selectAllByMonthSQL = 
				"select\r\n" + 
				"    diary.diary_number	\r\n" + 
				"    , diary.user_id	\r\n" + 
				"    , diary.category_id	\r\n" + 
				"    , category.category_name	\r\n" + 
				"    , diary.diary_date	\r\n" + 
				"    , diary.diary_subject	\r\n" + 
				"    , diary.hashtag	\r\n" + 
				"    , diary.diary_contents	\r\n" + 
				"    , diary.diary_img	\r\n" + 
				"from \r\n" + 
				"    plz_diary diary	\r\n" + 
				"    left outer join plz_diary_category category	\r\n" + 
				"        on diary.category_id = category.category_id	\r\n" + 
				"where \r\n" + 
				"    diary.user_id = ?	\r\n" + 
				"    and diary.diary_date BETWEEN to_date(?, 'yyyy/mm') and add_months(to_date(?, 'yyyy/mm'), 1) \r\n"+
				"order by diary_date";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectAllByMonthSQL);
			int index = 0;
			pstmt.setString(++index, user.getUser_id());
			pstmt.setString(++index, month);
			pstmt.setString(++index, month);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DiaryDto dto = new DiaryDto();
				dto.setDiary_number(rs.getInt("diary_number"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setCategory_id(rs.getString("category_id"));
				dto.setCategory_name(rs.getString("category_name"));
				dto.setDiary_date(rs.getDate("diary_date"));
				dto.setDiary_subject(rs.getString("diary_subject"));
				dto.setHashtag(rs.getString("hashtag"));
				dto.setDiary_contents(rs.getString("diary_contents"));
				dto.setDiary_img(rs.getString("diary_img"));
				
				list.add(dto);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<DiaryImgDto> selectAllByMonth2(String month, UserDto user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DiaryImgDto> list = new ArrayList<DiaryImgDto>();
		String selectAllByMonthSQL = 
				"select *\r\n" + 
				"from plz_diary_img\r\n" + 
				"where diary.user_id = ?\r\n" + 
				"and diary_date BETWEEN to_date(?, 'yyyy/mm') and add_months(to_date(?, 'yyyy/mm'), 1) \r\n"+
				"order by diary_date";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectAllByMonthSQL);
			int index = 0;
			pstmt.setString(++index, month);
			pstmt.setString(++index, month);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DiaryImgDto dto = new DiaryImgDto();
				dto.setUser_id(rs.getString("user_id"));
				dto.setDiary_date(rs.getDate("diary_date"));
				dto.setImage_name(rs.getString("image_name"));
				
				list.add(dto);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//diary_number 값을 이용해서 다이어리 전체 내용을 불러오는 부분
	//정상이면 객체, 비정상이면 null
	public DiaryDto selectById(int diaryNumber) {
		DiaryDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectByIdSQL = 
				"select 	\r\n" + 
				"    diary.diary_number	\r\n" + 
				"    , diary.user_id	\r\n" + 
				"    , diary.category_id	\r\n" + 
				"    , cate.category_name	\r\n" + 
				"    , diary.diary_date	\r\n" + 
				"    , diary.diary_subject	\r\n" + 
				"    , diary.hashtag	\r\n" + 
				"    , diary.diary_contents	\r\n" + 
				"    , diary.diary_img	\r\n" + 
				"from \r\n" + 
				"    plz_diary diary\r\n" + 
				"    left outer join plz_diary_category cate\r\n" + 
				"        on diary.category_id = cate.category_id\r\n" + 
				"where \r\n" + 
				"    diary.diary_number = ?";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setInt(1, diaryNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new DiaryDto();
				dto.setDiary_number(rs.getInt("diary_number"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setCategory_id(rs.getString("category_id"));
				dto.setCategory_name(rs.getString("category_name"));
				dto.setDiary_date(rs.getDate("diary_date"));
				dto.setDiary_subject(rs.getString("diary_subject"));
				dto.setHashtag(rs.getString("hashtag"));
				dto.setDiary_contents(rs.getString("diary_contents"));
				dto.setDiary_img(rs.getString("diary_img"));
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println(dto);
			DBClose.close(conn, pstmt, rs);
		}
		
		return dto;
	}
	

	
	
	public static void main(String[] args) throws ParseException {
		/*
		System.out.println("★★★★★★★★DAO★★★★★★★★★★");
		DiaryDto dto = new DiaryDto();
		UserDto user = new UserDto();
		user.setUser_id("mnmm97");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		dto.setCategory_id("1");
		dto.setDiary_contents("오늘은 댕댕이랑 산책을 했다.");
		dto.setDiary_date(sdf.parse("2019/06/12 16:24:33"));
		dto.setDiary_subject("오늘의 댕댕이 산책");
		dto.setHashtag("#산책#개꿀");
		dto.setUser_id("mnmm97");
		
		diaryDao dao = new diaryDao();
		int result = dao.insertDiary(dto);
		System.out.println(dto.getDiary_date().toString());
		//List<DiaryDto>list = dao.selectAllByMonth("2019/06", user);
		System.out.println(result);
		 */
	}

	public int insertImage(UserDto user, DiaryImgDto diaryImgDto) {
		// userID, diary_Date, img_name	
				System.out.println("> Img 저장 관련 DB 접근 성공!");
				Connection conn = null;
				PreparedStatement pstmt = null;
				int result = -1;
				String insertDiarySQL = 
						"insert into plz_diary_img \r\n" + 
						"values(?,?,?)";
				
				try {
					conn = DBConnection.makeConnection();
					conn.setAutoCommit(false);
					
					pstmt = conn.prepareStatement(insertDiarySQL);
					int index = 0;
					
					diaryImgDto.setUser_id(user.getUser_id());
					Date date = new Date(diaryImgDto.getDiary_date().getTime()); 
					System.out.println("	> " + diaryImgDto);
					
					pstmt.setString(++index, diaryImgDto.getUser_id());
					pstmt.setDate(++index, date);
					pstmt.setString(++index, diaryImgDto.getImage_name());
					
					pstmt.executeUpdate();
					//result = selectDiaryNumber(conn);
					
					conn.commit();
					System.out.println("	> [성공] DTO결과 불러오기 : " + diaryImgDto);
					
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				} finally {
					DBClose.close(conn, pstmt, null);
					System.out.println("> DB를 종료합니다.");
				}
				
				return result;	
	}

}
