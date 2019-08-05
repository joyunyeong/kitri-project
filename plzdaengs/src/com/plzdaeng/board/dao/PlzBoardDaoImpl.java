package com.plzdaeng.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.plzdaeng.board.model.PlzBoard;
import com.plzdaeng.board.model.PlzBoardCategory;
import com.plzdaeng.board.model.PlzReply;
import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;


public class PlzBoardDaoImpl implements PlzBoardDao{

	private static PlzBoardDao plzBoardDao; //2
	
	static {
		plzBoardDao = new PlzBoardDaoImpl();
	} //3
	
	public static PlzBoardDao getPlzBoardDao() {
		return plzBoardDao;
	} //4
	
	@Override
	public List<PlzBoardCategory> getCategoryList() {
		// TODO Auto-generated method stub
		List<PlzBoardCategory> list = new ArrayList<PlzBoardCategory>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT BOARD_CATEGORY_ID \n");
			sql.append("	 , BOARD_CATEGORY_NAME \n");
			sql.append("	 , BOARD_CATEGORY_DESCRIPTON \n");
			sql.append("FROM PLZ_BOARD_CATEGORY \n");
			sql.append("ORDER BY BOARD_CATEGORY_ID ASC ");
			pstm = con.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()) {
				PlzBoardCategory category = new PlzBoardCategory();
				category.setBoard_category_id(rs.getString("BOARD_CATEGORY_ID"));
				category.setBoard_category_name(rs.getString("BOARD_CATEGORY_NAME"));
				category.setBoard_category_descripton(rs.getString("BOARD_CATEGORY_DESCRIPTON"));
				
				list.add(category);
			}
			
			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return list;
	}

	@Override
	public int insertBoard(PlzBoard board) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO PLZ_BOARD \n");
			sql.append("VALUES(?,?,?,?,?,SYSDATE,NULL,0,NULL)");
			
			System.out.println(board);
//			SEQ_PLZ_BOARD.NEXTVAL
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, board.getPost_id());
			pstm.setString(2, board.getBoard_category_id());
			pstm.setString(3, board.getUser_id());
			pstm.setString(4, board.getPost_subject());
			pstm.setString(5, board.getPost_contents());
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		
		return result;
	}

	@Override
	public List<PlzBoard> getBoardList(PlzBoard board) {
		// TODO Auto-generated method stub
		List<PlzBoard> list = new ArrayList<PlzBoard>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT NO \n");
			sql.append("    , POST_ID\n");
			sql.append("    , BOARD_CATEGORY_ID\n");
			sql.append("    , BOARD_CATEGORY_DESCRIPTON \n");
			sql.append("    , POST_SUBJECT \n");
			sql.append("    , USER_ID \n");
			sql.append("    , NICKNAME \n");
			sql.append("    , VIEWS \n");
			sql.append("    , CREAT_DATE  \n");
			sql.append("FROM( \n");
			sql.append("    SELECT ROWNUM NO \n");
			sql.append("        , B.POST_ID \n");
			sql.append("        , C.BOARD_CATEGORY_ID \n");
			sql.append("        , C.BOARD_CATEGORY_DESCRIPTON \n");
			sql.append("        , B.POST_SUBJECT \n");
			sql.append("        , B.USER_ID \n");
			sql.append("        , U.NICKNAME \n");
			sql.append("        , B.VIEWS \n");
			sql.append("        , TO_CHAR(B.CREAT_DATE,'YYYY-MM-DD') AS CREAT_DATE \n");
			sql.append("    FROM PLZ_BOARD B \n");
			sql.append("        ,PLZ_BOARD_CATEGORY C \n");
			sql.append("        ,PLZ_USER U \n");
			sql.append("    WHERE B.BOARD_CATEGORY_ID = C.BOARD_CATEGORY_ID \n");
			sql.append("    AND B.USER_ID = U.USER_ID \n");
			if(!"".equals(board.getSearchValue()) && null != board.getSearchValue()) {
				if("1".equals(board.getSearchGubun())) {
					sql.append("	AND B.POST_SUBJECT LIKE '%' || ? || '%' \n");
				}else if("2".equals(board.getSearchGubun())) {
					sql.append("	AND U.NICKNAME LIKE '%' || ? || '%'\n");
				}
			}
			sql.append("	ORDER BY POST_ID DESC \n");
			
			sql.append(") \n");
			sql.append("WHERE NO BETWEEN (?-1) * ?+1 AND (? * ?)-1 \n");
			sql.append("ORDER BY NO DESC");
			
			System.out.println(sql.toString());
			
			pstm = con.prepareStatement(sql.toString());
			
			if(!"".equals(board.getSearchValue()) && null != board.getSearchValue()) {
				System.out.println("search gubun in ");
				pstm.setString(1, board.getSearchValue());
				pstm.setInt(2, board.getCurPage());
				pstm.setInt(3, board.getPagescale());
				pstm.setInt(4, board.getCurPage());
				pstm.setInt(5, board.getPagescale());
			}else {
				pstm.setInt(1, board.getCurPage());
				pstm.setInt(2, board.getPagescale());
				pstm.setInt(3, board.getCurPage());
				pstm.setInt(4, board.getPagescale());
			}
			
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				PlzBoard resultBoard = new PlzBoard();
				resultBoard.setNo(rs.getInt("NO"));
				resultBoard.setPost_id(rs.getInt("POST_ID"));
				resultBoard.setBoard_category_id(rs.getString("BOARD_CATEGORY_ID"));
				resultBoard.setBoard_category_descripton(rs.getString("BOARD_CATEGORY_DESCRIPTON"));
				resultBoard.setPost_subject(rs.getString("POST_SUBJECT"));
				resultBoard.setUser_id(rs.getString("USER_ID"));
				resultBoard.setNickname(rs.getString("NICKNAME"));
				resultBoard.setViews(rs.getInt("VIEWS"));
				resultBoard.setCreat_date(rs.getString("CREAT_DATE"));
				
				list.add(resultBoard);
			}
			
			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return list;
	}

	@Override
	public int getBoardTotalCnt() {
		// TODO Auto-generated method stub
		int ttlCnt = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) AS TTLCNT \n");
			sql.append("FROM PLZ_BOARD \n");
			pstm = con.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()) {
				ttlCnt = rs.getInt("TTLCNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return ttlCnt;
	}

	
	@Override
	public int updateViews(int post_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE PLZ_BOARD \n");
			sql.append("SET VIEWS = (SELECT VIEWS+1 \n");
			sql.append("				FROM PLZ_BOARD \n");
			sql.append("				WHERE POST_ID = ? ) \n");
			sql.append("WHERE POST_ID = ? \n");
			
			
				
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, post_id);
			pstm.setInt(2, post_id);
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		
		return result;
	}

	@Override
	public PlzBoard getBoardDetail(int post_id) {
		PlzBoard board = new PlzBoard();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT B.POST_ID \n");
			sql.append("	, C.BOARD_CATEGORY_ID \n");
			sql.append("	, C.BOARD_CATEGORY_DESCRIPTON \n");
			sql.append("	, B.POST_SUBJECT \n");
			sql.append("	, B.POST_CONTENTS\n");
			sql.append("	, B.USER_ID \n");
			sql.append("	, U.NICKNAME \n");
			sql.append("	, B.VIEWS \n");
			sql.append("	, TO_CHAR(B.CREAT_DATE,'YYYY-MM-DD') AS CREAT_DATE\n");
			sql.append("FROM PLZ_BOARD B \n");
			sql.append("	,PLZ_BOARD_CATEGORY C \n");
			sql.append("	,PLZ_USER U \n");
			sql.append("WHERE B.BOARD_CATEGORY_ID = C.BOARD_CATEGORY_ID \n");
			sql.append("AND B.USER_ID = U.USER_ID \n");
			sql.append("AND POST_ID = ? \n");
			
			
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, post_id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				board.setPost_id(rs.getInt("POST_ID"));
				board.setBoard_category_id(rs.getString("BOARD_CATEGORY_ID"));
				board.setBoard_category_descripton(rs.getString("BOARD_CATEGORY_DESCRIPTON"));
				board.setPost_subject(rs.getString("POST_SUBJECT"));
				board.setPost_contents(rs.getString("POST_CONTENTS"));
				board.setUser_id(rs.getString("USER_ID"));
				board.setNickname(rs.getString("NICKNAME"));
				board.setViews(rs.getInt("VIEWS"));
				board.setCreat_date(rs.getString("CREAT_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return board;
	}

	@Override
	public int updateBoard(PlzBoard board) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE PLZ_BOARD \n");
			sql.append("SET BOARD_CATEGORY_ID = ?\n");
			sql.append("	, POST_SUBJECT = ?  \n");
			sql.append("	, POST_CONTENTS = ? \n");
			sql.append("WHERE POST_ID = ?");
			
			
				
			pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, board.getBoard_category_id());
			pstm.setString(2, board.getPost_subject());
			pstm.setString(3, board.getPost_contents());
			pstm.setInt(4, board.getPost_id());
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		
		return result;
	}

	@Override
	public int getPostId() {
		// TODO Auto-generated method stub
		int post_id = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SEQ_PLZ_BOARD.NEXTVAL AS POST_ID \n");
			sql.append("FROM DUAL \n");
			pstm = con.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()) {
				post_id = rs.getInt("POST_ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return post_id;
	}

	@Override
	public int insertReply(PlzReply reply) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO PLZ_REPLY \n");
			sql.append("VALUES(SEQ_PLZ_REPLY.NEXTVAL,?,?,?,?,SYSDATE)");
			
//			SEQ_PLZ_BOARD.NEXTVAL
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, reply.getPost_id());
			pstm.setString(2, reply.getBoard_category_id());
			pstm.setString(3, reply.getUser_id());
			pstm.setString(4, reply.getReply_contents());
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		
		return result;
	}

	@Override
	public List<PlzReply> getReplyList(PlzReply reply) {
		// TODO Auto-generated method stub
		List<PlzReply> list = new ArrayList<PlzReply>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT NO \n");
			sql.append("    , REPLY_ID\n");
			sql.append("    , POST_ID\n");
			sql.append("    , USER_ID \n");
			sql.append("    , NICKNAME \n");
			sql.append("    , REPLY_CONTENTS \n");
			sql.append("    , CREAT_DATE  \n");
			sql.append("FROM( \n");
			sql.append("    SELECT ROWNUM NO \n");
			sql.append("        , R.REPLY_ID \n");
			sql.append("        , R.POST_ID \n");
			sql.append("        , R.BOARD_CATEGORY_ID \n");
			sql.append("        , R.USER_ID \n");
			sql.append("        , U.NICKNAME \n");
			sql.append("        , R.REPLY_CONTENTS \n");
			sql.append("        , TO_CHAR(R.CREAT_DATE,'YYYY-MM-DD') AS CREAT_DATE \n");
			sql.append("    FROM PLZ_REPLY R \n");
			sql.append("        ,PLZ_USER U \n");
			sql.append("    WHERE R.POST_ID = ? \n");
			sql.append("    AND R.USER_ID = U.USER_ID \n");
			sql.append("	ORDER BY R.REPLY_ID DESC \n");
			sql.append(") \n");
			sql.append("WHERE NO BETWEEN (?-1) * ?+1 AND (? * ?) \n");
			
			System.out.println(sql.toString());
			
			pstm = con.prepareStatement(sql.toString());
			
				System.out.println("search gubun in ");
				pstm.setInt(1, reply.getPost_id());
				pstm.setInt(2, reply.getrCurPage());
				pstm.setInt(3, reply.getrPagescale());
				pstm.setInt(4, reply.getrCurPage());
				pstm.setInt(5, reply.getrPagescale());
			
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				PlzReply resultReply = new PlzReply();
				
				resultReply.setNo(rs.getInt("NO"));
				resultReply.setReply_id(rs.getInt("REPLY_ID"));
				resultReply.setPost_id(rs.getInt("POST_ID"));
				resultReply.setUser_id(rs.getString("USER_ID"));
				resultReply.setUser_name(rs.getString("NICKNAME"));
				resultReply.setReply_contents(rs.getString("REPLY_CONTENTS"));
				resultReply.setCreat_date(rs.getString("CREAT_DATE"));
				
				list.add(resultReply);
			}
			
			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return list;
	}

	@Override
	public int getReplyTotalCnt(int post_id) {
		// TODO Auto-generated method stub
		int ttlCnt = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) AS TTLCNT \n");
			sql.append("FROM PLZ_REPLY \n");
			sql.append("WHERE POST_ID = ? \n");
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, post_id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ttlCnt = rs.getInt("TTLCNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return ttlCnt;
	}

	@Override
	public int deleteBoard(int post_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE PLZ_REPLY \n");
			sql.append("WHERE POST_ID=?");
			
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, post_id);
			
			result = pstm.executeUpdate();
			
			sql.setLength(0);
			sql.append("DELETE PLZ_BOARD \n");
			sql.append("WHERE POST_ID=?");
			
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, post_id);
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		
		return result;
	}

	@Override
	public int deleteRelply(int reply_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE PLZ_REPLY \n");
			sql.append("WHERE REPLY_ID=?");
			
			System.out.println(sql.toString());
			pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, reply_id);
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		
		return result;
	}
	
	
	
	
}
