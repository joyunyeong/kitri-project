package com.plzdaeng.group.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.plzdaeng.group.model.*;
import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;

public class GroupDaoImpl implements GroupDao {

	private static GroupDaoImpl groupDaoImpl;

	static {
		groupDaoImpl = new GroupDaoImpl();
	}

	private GroupDaoImpl() {
	}

	public static GroupDaoImpl getGroupDaoImpl() {
		return groupDaoImpl;
	}

	@Override
	public int createGroup(GroupDto dto) {
		System.out.println("Dao메소드입장");
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuffer creatGroupSql = new StringBuffer();

		creatGroupSql.append("insert into PLZ_GROUP(\r\n");
		creatGroupSql.append("GROUP_ID\r\n");
		creatGroupSql.append(",GROUP_CATEGORY_ID\r\n");
		creatGroupSql.append(",GROUP_NAME\r\n");
		creatGroupSql.append(",DESCRIPTION\r\n");
		creatGroupSql.append(",GROUP_IMG\r\n");
		creatGroupSql.append(",ADDRESS_SIDO)\r\n");
		creatGroupSql.append("values(\r\n");
		creatGroupSql.append("group_id_seq.NEXTVAL\r\n");
		creatGroupSql.append(", ?\r\n");
		creatGroupSql.append(", ?\r\n");
		creatGroupSql.append(", ?\r\n");
		creatGroupSql.append(", ?\r\n");
		creatGroupSql.append(", ?)\r\n");

		try {
			System.out.println("sql문실행전");
			System.out.println(dto);
			conn = DBConnection.makeConnectplzdb();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(creatGroupSql.toString());
			pstmt.setString(1, dto.getGroupCategory().getGroup_category_id());
			pstmt.setString(2, dto.getGroup_name());
			pstmt.setString(3, dto.getGroup_description());
			pstmt.setString(4, dto.getGroup_img());
			pstmt.setString(5, dto.getAddress_sido());
			int r = pstmt.executeUpdate();
			System.out.println("sql문실행후");

			System.out.println(r);
			DBClose.close(null, pstmt);

			applyGroupCategory(conn, dto);

			conn.commit();
			result = 1;
		} catch (SQLException e) {
			try {
				System.out.println("rollback 실행");
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("rollback fail..관리자에게 문의하세요.");
				e1.printStackTrace();
				result = -2;
				return result;
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, null);
		}

		return result;
	}

	@Override
	public void applyGroupCategory(Connection conn, GroupDto dto) throws SQLException {
		PreparedStatement pstmt = null;
		StringBuffer applyCategorySql = new StringBuffer("");
		applyCategorySql.append("insert into PLZ_GROUP_MEMBER values(group_id_seq.CURRVAL, ?, 'L', sysdate)");
		try {
			pstmt = conn.prepareStatement(applyCategorySql.toString());
			pstmt.setString(1, dto.getGroup_leader());
			int r = pstmt.executeUpdate();
			System.out.println(r);
		} finally {
			DBClose.close(null, pstmt);
		}
	}

	@Override
	public int changeGroup(GroupDto dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String updateGroupSql = "";
		updateGroupSql += "UPDATE plz_group\r\n" + 
				"SET DESCRIPTION = ?,\r\n" + 
				"GROUP_IMG = ?,\r\n" + 
				"ADDRESS_SIDO = ?\r\n" + 
				"WHERE group_id = ?";

		try {
		
			System.out.println("sql문실행전");
			conn = DBConnection.makeConnectplzdb();
			pstmt = conn.prepareStatement(updateGroupSql);

			pstmt.setString(1, dto.getGroup_description());
			pstmt.setString(2, dto.getGroup_img());
			pstmt.setString(3, dto.getAddress_sido());
			pstmt.setInt(4, dto.getGroup_id());
			
			int r = pstmt.executeUpdate();
			System.out.println("변경된 row : "+r);
			if(r == 1) {
				result = 1;
			}
			
			System.out.println("sql문실행후");

			DBClose.close(conn, pstmt);

		} catch (SQLException e) {
			System.out.println("groupdetail update fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return result;
	}
	
	@Override
	public int deleteGroupmember(Connection conn, int group_id) throws SQLException {
		int result = -1;

		System.out.println("Dao메소드입장");
		
		
		PreparedStatement pstmt = null;

		String deleteGroupSql = "";
		deleteGroupSql += "delete \r\n" + 
				"FROM plz_group_member\r\n" + 
				"WHERE group_id = ?;";

		
			System.out.println("sql문실행전");
			
			
			pstmt = conn.prepareStatement(deleteGroupSql);
			pstmt.setInt(1, group_id);

			int r = pstmt.executeUpdate();
			System.out.println("sql문실행후");

			System.out.println("변경된 row : " + r);
			DBClose.close(null, pstmt);

			if(r>=1) {
			result = 1;
			}
			
		
			DBClose.close(null, pstmt);
	

		return result;

	}

	@Override
	public int deleteGroup(int group_id) {
		
		int result = -1;

		System.out.println("Dao메소드입장");
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String deleteGroupSql = "";
		deleteGroupSql += "DELETE\r\n" + 
				"FROM plz_group\r\n" + 
				"WHERE group_id = ?;";

		try {
			System.out.println("sql문실행전");
			
			conn = DBConnection.makeConnectplzdb();
			conn.setAutoCommit(false);
			deleteGroupmember(conn, group_id);
			
			pstmt = conn.prepareStatement(deleteGroupSql);
			pstmt.setInt(1, group_id);

			int r = pstmt.executeUpdate();
			System.out.println("sql문실행후");

			System.out.println(r);
			if(r == 1) {
				result = 1;
				conn.commit();
			}

		} catch (SQLException e) {
			try {
				System.out.println("rollback 실행");
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("rollback fail..관리자에게 문의하세요.");
				e1.printStackTrace();
				result = -2;
				return result;
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return result;
		
		
		
	}

	@Override
	public List<GroupDto> myGroup(String user_id) {

		List<GroupDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inGourpSQL = "";
		inGourpSQL += "SELECT g.group_id , g.group_name, g.description "
				+ ", t.group_category_id, t.group_category_name, g.group_img "
				+ ", g.address_sido, m.user_id \r\n"
				+ "FROM plz_group g inner join plz_group_type t \r\n"
				+ "on g.group_category_id = t.group_category_id \r\n" + "inner join plz_group_member m \r\n"
				+ "on g.group_id = m.group_id \r\n" + "WHERE m.user_id = ? and m.member_status != all('X', 'A')"
				+ "ORDER BY g.group_id, m.user_id DESC";

		try {
			conn = DBConnection.makeConnectplzdb();
			pstmt = conn.prepareStatement(inGourpSQL.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			GroupDto dto = null;
			GroupCategory cate = null;
			GroupMember member = null;
			List<GroupMember> memberList = null;
			int old_group_id = -1;
			int group_id = 0;
			while (rs.next()) {
				group_id = rs.getInt("group_id");
				if (old_group_id != group_id) {

					cate = new GroupCategory();
					cate.setGroup_category_id(rs.getString("group_category_id"));
					cate.setGroup_category_name(rs.getString("group_category_name"));

					dto = new GroupDto();
					list.add(dto);
					dto.setGroupCategory(cate);
					dto.setGroup_id(rs.getInt("group_id"));
					dto.setGroup_name(rs.getString("group_name"));
					dto.setGroup_description(rs.getString("description"));
//				dto.setGroup_leader(group_leader);
					dto.setGroup_img(rs.getString("group_img"));
					dto.setAddress_sido(rs.getString("address_sido"));
					

					memberList = new ArrayList<GroupMember>();
					dto.setGroupMembers(memberList);

				}

				member = new GroupMember();
				member.setUser_id(rs.getString("user_id"));
				// member.setMember_status(rs.getString("member_status"));
				memberList.add(member);

			}

		} catch (SQLException e) {
			System.out.println("myGroup() error");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<GroupDto> recommendGroup(String user_id) {

		List<GroupDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inGourpSQL = "";
		inGourpSQL += "SELECT g.group_id , g.group_name, g.description , g.group_category_id\r\n" + 
				", t.group_category_name, g.group_img , g.address_sido\r\n" + 
				"FROM plz_group g inner join plz_group_type t\r\n" + 
				"ON g.group_category_id = t.group_category_id\r\n" + 
				"WHERE g.group_id not in((select mm.group_id\r\n" + 
				"from (Select m.group_id, m.user_id, m.member_status\r\n" + 
				"from plz_group_member m\r\n" + 
				"where m.user_id = ?) mm\r\n" + 
				"where mm.member_status not in('A', 'X')))";

		try {
			conn = DBConnection.makeConnectplzdb();
			pstmt = conn.prepareStatement(inGourpSQL.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			GroupDto dto = null;
			GroupCategory cate = null;
			GroupMember member = null;
			List<GroupMember> memberList = null;
			int old_group_id = -1;
			int group_id = 0;
			while (rs.next()) {
				group_id = rs.getInt("group_id");
				if (old_group_id != group_id) {

					cate = new GroupCategory();
					cate.setGroup_category_id(rs.getString("group_category_id"));
					cate.setGroup_category_name(rs.getString("group_category_name"));

					dto = new GroupDto();
					list.add(dto);
					dto.setGroupCategory(cate);
					dto.setGroup_id(rs.getInt("group_id"));
					dto.setGroup_name(rs.getString("group_name"));
					dto.setGroup_description(rs.getString("description"));
//				dto.setGroup_leader(group_leader);
					dto.setGroup_img(rs.getString("group_img"));
					dto.setAddress_sido(rs.getString("address_sido"));

					memberList = new ArrayList<GroupMember>();
					dto.setGroupMembers(memberList);

				}

//				member = new GroupMember();
//				member.setUser_id(rs.getString("user_id"));
//				// member.setMember_status(rs.getString("member_status"));
//				memberList.add(member);
			}

		} catch (SQLException e) {
			System.out.println("recommendGroup() error");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<GroupDto> searchGroup(String key, String word) {

		List<GroupDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnectplzdb();

			String inGroupSQL = "SELECT g.group_id , g.group_name, g.description \r\n"
					+ ", t.group_category_id, t.group_category_name, g.group_img \r\n"
					+ ", g.address_sido, m.user_id \r\n"
					+ "FROM plz_group g inner join plz_group_type t \r\n"
					+ "on g.group_category_id = t.group_category_id \r\n" + "inner join plz_group_member m \r\n"
					+ "on g.group_id = m.group_id \r\n";

			if (key.equals("1")) {
				inGroupSQL += "WHERE g.group_name like '%" + word + "%' \r\n";
				// pstmt = conn.prepareStatement(inGourpSQL);
//				pstmt.setString(1, word);
			} else if (key.equals("2")) {
				inGroupSQL += "WHERE g.address_sido like '%" + word + "%' or g.address_sigungu like '%" + word
						+ "%' \r\n";
				// pstmt = conn.prepareStatement(inGourpSQL);
//				pstmt.setString(1, word);
//				pstmt.setString(2, word);
			} else if (key.equals("3")) {
				inGroupSQL += "WHERE t.group_category_name like '%" + word + "%' \r\n";
				// pstmt = conn.prepareStatement(inGourpSQL);
//				pstmt.setString(1, word);
			}
			inGroupSQL += "ORDER BY g.group_id, m.user_id DESC";
			pstmt = conn.prepareStatement(inGroupSQL);
			rs = pstmt.executeQuery();
			GroupDto dto = null;
			GroupCategory cate = null;
			GroupMember member = null;
			List<GroupMember> memberList = null;
			int old_group_id = -1;
			int group_id = 0;
			while (rs.next()) {
				group_id = rs.getInt("group_id");
				if (old_group_id != group_id) {

					cate = new GroupCategory();
					cate.setGroup_category_id(rs.getString("group_category_id"));
					cate.setGroup_category_name(rs.getString("group_category_name"));

					dto = new GroupDto();
					list.add(dto);
					dto.setGroupCategory(cate);
					dto.setGroup_id(rs.getInt("group_id"));
					dto.setGroup_name(rs.getString("group_name"));
					dto.setGroup_description(rs.getString("description"));
//				dto.setGroup_leader(group_leader);
					dto.setGroup_img(rs.getString("group_img"));
					dto.setAddress_sido(rs.getString("address_sido"));

					memberList = new ArrayList<GroupMember>();
					dto.setGroupMembers(memberList);

				}

				member = new GroupMember();
				member.setUser_id(rs.getString("user_id"));
				// member.setMember_status(rs.getString("member_status"));
				memberList.add(member);
			}

		} catch (SQLException e) {
			System.out.println("searchGroup() error");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return list;
	}

	@Override
	public String inquiry(int group_id, String user_id) {
		String result = "X";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnectplzdb();
			String checkSQL = "";
			checkSQL += "SELECT group_id, user_id, member_status \r\n" + 
								"FROM plz_group_member \r\n" +
								"WHERE group_id = ? and user_id = ? and member_status in('L','M','A')";

			pstmt = conn.prepareStatement(checkSQL);
			pstmt.setInt(1, group_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				
				result = rs.getString("member_status");
				
				
			}
			
		} catch (SQLException e) {
			System.out.println("inquiry() error");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return result;
	}

	// 그룹 첫페이지 게시판 로딩 ( 게시판 or 사진 게시판 )
	@Override
	public List<GroupBoard> boardLoading(int group_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnectplzdb();

			String boradLoadingSQL = "SELECT post_id, user_id, post_subject, post_contents, creat_date, img_id, views, group_id\r\n" + 
					"FROM plz_board b\r\n" + 
					"WHERE group_id = ?;";

			pstmt = conn.prepareStatement(boradLoadingSQL);
			pstmt.setInt(1, group_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				
		
				
			}

		} catch (SQLException e) {
			System.out.println("boardLoading() error");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}

	@Override
	public GroupDto groupDetail(int group_id) {
		System.out.println("Dao메소드입장");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String groupDetailSQL = "";
		GroupDto dto = null;
		groupDetailSQL = "SELECT g.GROUP_ID, t.GROUP_CATEGORY_id, t.GROUP_CATEGORY_NAME, g.GROUP_NAME, g.DESCRIPTION, g.GROUP_img, g.ADDRESS_SIDO\r\n" + 
				"FROM plz_group g inner join plz_group_type t\r\n" + 
				"ON g.group_category_id = t.group_category_id\r\n" + 
				"WHERE g.group_id = ?";
	

		try {
			//System.out.println("sql문실행전");
			conn = DBConnection.makeConnectplzdb();
			
			pstmt = conn.prepareStatement(groupDetailSQL);
			pstmt.setInt(1, group_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new GroupDto();
				GroupCategory cate = new GroupCategory();
				cate.setGroup_category_id(rs.getString("GROUP_CATEGORY_id"));
				cate.setGroup_category_name(rs.getString("GROUP_CATEGORY_name"));
				dto.setGroupCategory(cate);
				
				dto.setGroup_id(rs.getInt("group_id"));
				dto.setGroup_name(rs.getString("group_name"));
				dto.setGroup_description(rs.getString("description"));
//			dto.setGroup_leader(group_leader);
				dto.setGroup_img(rs.getString("group_img"));
				dto.setAddress_sido(rs.getString("address_sido"));
				
				
			}
			
			//System.out.println("sql문실행후");
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, null);
		}

		return dto;
	}

	@Override
	public int joinGroup(int group_id, String id) {
		int result = -1;

		System.out.println("Dao메소드입장");
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String joinGroupSql = "";
		joinGroupSql += "insert into plz_group_member (group_id, user_id, member_status, group_joindate)\r\n" + 
						"values(?, ?, 'A', sysdate)";

		
			System.out.println("sql문실행전");
			
			
			try {
				conn = DBConnection.makeConnection();
				pstmt = conn.prepareStatement(joinGroupSql);
			
				pstmt.setInt(1, group_id);
				pstmt.setString(2, id);

			int r = pstmt.executeUpdate();
			System.out.println("sql문실행후");

			System.out.println("변경된 row : " + r);

			if(r>=1) {
			result = 1;
			}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			DBClose.close(conn, pstmt);
			}

		return result;

	}


	@Override
	public int goOutGroup(int group_id, String id) {
		int result = -1;

		System.out.println("Dao메소드입장");
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String deletememberSQL = "update plz_group_member\r\n" + 
				"set member_status = 'X'\r\n" + 
				"where group_id = ? and user_id = ?";
		
				
		try {
			conn = DBConnection.makeConnectplzdb();
			pstmt = conn.prepareStatement(deletememberSQL);
			
			pstmt.setInt(1, group_id);
			pstmt.setString(2, id);
			int r = pstmt.executeUpdate();
			if(r > 0) {
				result = 1;
			}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			DBClose.close(conn, pstmt);
			}

		return result;

	}
	

	public List<GroupMember> memberlist(int group_id) {
		
		List<GroupMember> list = null;
		GroupMember member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberListSQL = "";
		memberListSQL += "SELECT m.group_id, m.user_id, u.nickname , m.member_status, m.group_joindate \r\n"
				+ "FROM plz_group_member m inner join plz_user u \r\n"
				+ "ON m.user_id = u.user_id \r\n"
				+ "WHERE group_id = ? and m.member_status != 'X'";
				
		try {
			conn = DBConnection.makeConnectplzdb();
			pstmt = conn.prepareStatement(memberListSQL);
			
			pstmt.setInt(1, group_id);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				member = new GroupMember();
				member.setGroup_id(rs.getInt("group_id"));
				member.setUser_id(rs.getString("user_id"));
				member.setNickName(rs.getString("nickname"));
				member.setMember_status(rs.getString("member_status"));
				member.setGroup_joindate(String.valueOf(rs.getDate("group_joindate")));
				
				list.add(member);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public int kickMember(GroupMember member) {
		int result = -1;		

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String memberListSQL = "update plz_group_member\r\n" + 
				"set member_status = 'X'\r\n" + 
				"where group_id = ? and user_id = ?";
		
				
		try {
			conn = DBConnection.makeConnectplzdb();
			pstmt = conn.prepareStatement(memberListSQL);
			
			pstmt.setInt(1, member.getGroup_id());
			pstmt.setString(2, member.getUser_id());
			int r = pstmt.executeUpdate();
			if(r > 0) {
				result = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, null);
		}
		return result;
	}

	@Override
	public int permitMember(GroupMember member) {
		int result = -1;		

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String memberListSQL = "update plz_group_member\r\n" + 
				"set member_status = 'M'\r\n" + 
				"where group_id = ? and user_id = ?";
		
				
		try {
			conn = DBConnection.makeConnectplzdb();
			pstmt = conn.prepareStatement(memberListSQL);
			
			pstmt.setInt(1, member.getGroup_id());
			pstmt.setString(2, member.getUser_id());
			int r = pstmt.executeUpdate();
			if(r > 0) {
				result = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, null);
		}
		return result;
	}

	@Override
	public int passAuthority(Connection conn, GroupMember member) throws SQLException {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String memberListSQL = "update plz_group_member\r\n" + 
				"set member_status = 'L'\r\n" + 
				"where group_id = ? and user_id = ?";
		
				
		try {
			conn = DBConnection.makeConnectplzdb();
			
			pstmt = conn.prepareStatement(memberListSQL);
			
			pstmt.setInt(1, member.getGroup_id());
			pstmt.setString(2, member.getUser_id());
			int r = pstmt.executeUpdate();
			if(r == 1) {
				System.out.println("권한 이동 성공");
				result = 1;
			}
		}finally {
			DBClose.close(null, pstmt, null);
		}
		return result;
	}

	@Override
	public int removeAuthority(GroupMember member) {
		int result = -1;		

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String memberListSQL = "update plz_group_member\r\n" + 
				"set member_status = 'M'\r\n" + 
				"where group_id = ? and member_status = 'L'";
		
				
		try {
			conn = DBConnection.makeConnectplzdb();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(memberListSQL);
			
			pstmt.setInt(1, member.getGroup_id());
			int r2 = pstmt.executeUpdate();
			
			int r1 = passAuthority(conn, member);
			if(r1 == 1 && r2 == 1) {
				result = 1;
				conn.commit();
			}

			
			} catch (SQLException e) {
				try {
				System.out.println("rollback 실행");
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} finally {
			DBClose.close(conn, pstmt, null);
		}
		return result;
	}

	@Override
	public GroupDto groupPageLoading(int group_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroupDto dto = new GroupDto();
		String pageLoadingSQL = "";
		pageLoadingSQL = "SELECT group_id, group_name, description\r\n" + 
				"FROM plz_group\r\n" + 
				"WHERE group_id = ?";
		
		try {
			conn = DBConnection.makeConnectplzdb();
			
			pstmt = conn.prepareStatement(pageLoadingSQL);
			pstmt.setInt(1, group_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setGroup_id(rs.getInt("group_id"));
				dto.setGroup_name(rs.getString("group_name"));
				dto.setGroup_description(rs.getString("description"));
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt, rs);
		}
		return dto;
	}

	
		

}
