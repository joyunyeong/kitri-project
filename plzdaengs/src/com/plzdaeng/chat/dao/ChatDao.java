package com.plzdaeng.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.plzdaeng.dto.ChatDto;
import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;

public class ChatDao {

	public int insert(ChatDto chatDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String insertSQL = 
				"insert into plz_group_chat(\r\n" + 
				"    group_id\r\n" + 
				"    , user_id\r\n" + 
				"    , chat_date\r\n" + 
				"    , chat_contents\r\n" + 
				")values(\r\n" + 
				"    ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , to_timestamp(? , 'yyyy/mm/dd hh24:mi:ss:ff3')\r\n" + 
				"    , ?	\r\n" + 
				")";
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(insertSQL);
			int index = 0;
			pstmt.setInt(++index, chatDto.getGroup_id());
			pstmt.setString(++index, chatDto.getUser_id());
			pstmt.setString(++index, chatDto.getChat_date());
			pstmt.setString(++index, chatDto.getChat_contents());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return result;
	}

	public List<ChatDto> selectAllByGroupId(String groupId) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		List<ChatDto> list = new ArrayList<ChatDto>();
		String selectAllByGroupIdSQL = 
				"select \r\n" + 
				"    chat.group_id\r\n" + 
				"    , chat.user_id\r\n" + 
				"    , puser.nickname\r\n" + 
				"    , to_char(chat.chat_date, 'yyyy/mm/dd hh24:mi:ss:ff3') as chat_date\r\n" + 
				"    , chat.chat_contents\r\n" + 
				"from\r\n" + 
				"    plz_group_chat chat\r\n" + 
				"    inner join plz_user puser\r\n" + 
				"        on chat.user_id = puser.user_id\r\n" + 
				"where chat.group_id = ?\r\n" + 
				"order by chat_date";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectAllByGroupIdSQL);
			pstmt.setString(1, groupId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ChatDto chatDto = new ChatDto();
				chatDto.setType("msg");
				chatDto.setGroup_id(rs.getInt("group_id"));
				chatDto.setUser_id(rs.getString("user_id"));
				chatDto.setNickname(rs.getString("nickname"));
				chatDto.setChat_date(rs.getString("chat_date"));
				chatDto.setChat_contents(rs.getString("chat_contents"));
				
				list.add(chatDto);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
}
