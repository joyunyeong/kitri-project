package com.plzdaeng.user.dao;

import java.sql.*;

import com.plzdaeng.dto.UserDetailDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;

public class UserDao {

	public int insert(UserDto userDto) {
		Connection con = null;
		int result = -1;
		try {
			con = DBConnection.makeConnection();
			con.setAutoCommit(false);
			insertUser(con, userDto);
			insertUserDetail(con, userDto);
			result = 1;
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBClose.close(con, null);
		}
		return result;
	}
	
	private void insertUserDetail(Connection con, UserDto userDto) throws SQLException {
		PreparedStatement pstmt = null;
		StringBuffer inserUserDetailSQL = new StringBuffer();
		inserUserDetailSQL.append("insert into plz_user_detail(\n");
		inserUserDetailSQL.append("    user_id\n");
		inserUserDetailSQL.append("    , tel\n");
		inserUserDetailSQL.append("    , gender\n");
		inserUserDetailSQL.append("    , zipcode\n");
		inserUserDetailSQL.append("    , address\n");
		inserUserDetailSQL.append("    , address_detail\n");
		inserUserDetailSQL.append(")values(\n");
		inserUserDetailSQL.append("    ?\n");
		inserUserDetailSQL.append("    , ?\n");
		inserUserDetailSQL.append("    , ?\n");
		inserUserDetailSQL.append("    , ?\n");
		inserUserDetailSQL.append("    , ?\n");
		inserUserDetailSQL.append("    , ?\n");
		inserUserDetailSQL.append(")\n");
		
		pstmt = con.prepareStatement(inserUserDetailSQL.toString());
		int index = 0;
		UserDetailDto userDetailDto = userDto.getUserDetailDto();
		pstmt.setString(++index, userDto.getUser_id());
		pstmt.setString(++index, userDetailDto.getTel());
		pstmt.setString(++index, userDetailDto.getGender());
		pstmt.setString(++index, userDetailDto.getZipcode());
		pstmt.setString(++index, userDetailDto.getAddress());
		pstmt.setString(++index, userDetailDto.getAddress_detail());
		
		pstmt.executeUpdate();
		
		DBClose.close(null, pstmt);
		
	}

	public void insertUser(Connection con, UserDto userDto) throws SQLException {
		PreparedStatement pstmt = null;
			
		StringBuffer insertUserSQL = new StringBuffer();
		insertUserSQL.append("insert into plz_user(\n");
		insertUserSQL.append("    user_id\n");
		insertUserSQL.append("    , password\n");
		insertUserSQL.append("    , authority\n");
		insertUserSQL.append("    , emailid\n");
		insertUserSQL.append("    , emaildomain\n");
		insertUserSQL.append("    , nickname\n");
		insertUserSQL.append("    , user_img\n");
		insertUserSQL.append(")values(\n");
		insertUserSQL.append("    ?\n");
		insertUserSQL.append("    , ?\n");
		insertUserSQL.append("    , ?\n");
		insertUserSQL.append("    , ?\n");
		insertUserSQL.append("    , ?\n");
		insertUserSQL.append("    , ?\n");
		insertUserSQL.append("    , ?\n");
		insertUserSQL.append(")\n");
		
		pstmt = con.prepareStatement(insertUserSQL.toString());
		int index = 0;
		pstmt.setString(++index, userDto.getUser_id());
		pstmt.setString(++index, userDto.getPassword());
		pstmt.setString(++index, userDto.getAuthority());
		pstmt.setString(++index, userDto.getEmailid());
		pstmt.setString(++index, userDto.getEmaildomain());
		pstmt.setString(++index, userDto.getNickname());
		pstmt.setString(++index, userDto.getUser_img());
		
		pstmt.executeUpdate();
		
		DBClose.close(null, pstmt);
	}
	
	//id체크용
	public int selectById(String id) {
		int result = 1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectByIdSQL = 
				"select count(user_id) count\r\n" + 
				"from plz_user\r\n" + 
				"where \r\n" + 
				"    user_id = ?";
		
		try {
			con = DBConnection.makeConnection();
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			rs.next();
			result = rs.getInt("count");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	//로그인용
	public UserDto selectById(UserDto userDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDto loginUser = null;
		String selectByIdSQL = 
				"select \r\n" + 
				"    user_id\r\n" + 
				"    , password\r\n" + 
				"    , emailid\r\n" + 
				"    , emaildomain\r\n" + 
				"    , nickname\r\n" + 
				"    , user_img\r\n" + 
				"	 , authority\r\n" + 
				"from plz_user\r\n" + 
				"where \r\n" + 
				"    user_id = ?\r\n" + 
				"    and password = ?";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectByIdSQL);
			int index = 0;
			pstmt.setString(++index, userDto.getUser_id());
			pstmt.setString(++index, userDto.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginUser = new UserDto();
				loginUser.setUser_id(rs.getString("user_id"));
				loginUser.setPassword(rs.getString("password"));
				loginUser.setEmailid(rs.getString("emailid"));
				loginUser.setEmaildomain(rs.getString("emaildomain"));
				loginUser.setNickname(rs.getString("nickname"));
				loginUser.setUser_img(rs.getString("user_img"));
				loginUser.setAuthority(rs.getString("authority"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return loginUser;
	}

	public UserDto selectDetailById(UserDto userDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDto dto = null;
		String selectDetailById = 
				"select \r\n" + 
				"    u.user_id\r\n" + 
				"    , u.password\r\n" + 
				"    , u.emailid\r\n" + 
				"    , u.emaildomain\r\n" + 
				"    , u.nickname\r\n" + 
				"    , u.user_img\r\n" + 
				"    , u.authority\r\n" + 
				"    , detail.tel\r\n" + 
				"    , detail.gender\r\n" + 
				"    , detail.zipcode\r\n" + 
				"    , detail.address\r\n" + 
				"    , detail.address_detail\r\n" + 
				"from \r\n" + 
				"    plz_user u\r\n" + 
				"    inner join plz_user_detail detail\r\n" + 
				"        on u.user_id = detail.user_id\r\n" + 
				"where \r\n" + 
				"    u.user_id = ?";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectDetailById);
			pstmt.setString(1, userDto.getUser_id());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDto();
				dto.setUser_id(rs.getString("user_id"));
				dto.setPassword(rs.getString("password"));
				dto.setEmailid(rs.getString("emailid"));
				dto.setEmaildomain(rs.getString("emaildomain"));
				dto.setNickname(rs.getString("nickname"));
				dto.setUser_img(rs.getString("user_img"));
				dto.setAuthority(rs.getString("authority"));
								
				UserDetailDto userDetailDto = new UserDetailDto();
				dto.setUserDetailDto(userDetailDto);
				
				userDetailDto.setTel(rs.getString("tel"));
				userDetailDto.setGender(rs.getString("gender"));
				userDetailDto.setZipcode(rs.getString("zipcode"));
				userDetailDto.setAddress(rs.getString("address"));
				userDetailDto.setAddress_detail(rs.getString("address_detail"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		
		return dto;
	}

	public int update(UserDto userDto) {
		Connection conn = null;
		int result = -1;
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			updateUser(conn, userDto);
			updateUserDetail(conn, userDto);
			
			result = 1;
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, null);
		}
		
		return result;
	}

	private void updateUserDetail(Connection conn, UserDto userDto) throws SQLException {
		PreparedStatement pstmt = null;
		String updateUserDetailSQL = 
				"update plz_user_detail	\r\n" + 
				"set	\r\n" + 
				"    tel = ?	\r\n" + 
				"    , gender = ?	\r\n" + 
				"    , zipcode = ?	\r\n" + 
				"    , address = ?	\r\n" + 
				"    , address_detail = ?	\r\n" + 
				"where\r\n" + 
				"    user_id = ?";
		pstmt = conn.prepareStatement(updateUserDetailSQL);
		int index = 0;
		pstmt.setString(++index, userDto.getUserDetailDto().getTel());
		pstmt.setString(++index, userDto.getUserDetailDto().getGender());
		pstmt.setString(++index, userDto.getUserDetailDto().getZipcode());
		pstmt.setString(++index, userDto.getUserDetailDto().getAddress());
		pstmt.setString(++index, userDto.getUserDetailDto().getAddress_detail());
		pstmt.setString(++index, userDto.getUser_id());
		
		pstmt.executeQuery();
		
	}

	private void updateUser(Connection con, UserDto userDto) throws SQLException {
		PreparedStatement pstmt = null;
		String updatUserSQL = 
				"update plz_user\r\n" + 
				"set\r\n" + 
				"    password = ?	\r\n" + 
				"    , emailid = ?	\r\n" + 
				"    , emaildomain = ?	\r\n" + 
				"    , nickname = ?	\r\n" + 
				"    , user_img = ? \r\n" + 
				"where\r\n" + 
				"    user_id = ?";
		
		pstmt = con.prepareStatement(updatUserSQL);
		int index = 0;
		pstmt.setString(++index, userDto.getPassword());
		pstmt.setString(++index, userDto.getEmailid());
		pstmt.setString(++index, userDto.getEmaildomain());
		pstmt.setString(++index, userDto.getNickname());
		pstmt.setString(++index, userDto.getUser_img());
		pstmt.setString(++index, userDto.getUser_id());
		
		pstmt.executeUpdate();
		
	}

}
