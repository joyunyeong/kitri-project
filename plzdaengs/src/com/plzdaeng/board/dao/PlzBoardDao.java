package com.plzdaeng.board.dao;

import java.util.List;

import com.plzdaeng.board.model.PlzBoard;
import com.plzdaeng.board.model.PlzBoardCategory;
import com.plzdaeng.board.model.PlzReply;

public interface PlzBoardDao {
	
	List<PlzBoardCategory> getCategoryList();
	int insertBoard(PlzBoard board);
	List<PlzBoard> getBoardList(PlzBoard board);
	int getBoardTotalCnt();
	PlzBoard getBoardDetail(int post_id);
	int updateViews(int post_id);
	int updateBoard(PlzBoard board);		//수정하기
	int getPostId();
	int insertReply(PlzReply reply);
	List<PlzReply> getReplyList(PlzReply reply);
	int getReplyTotalCnt(int post_id);
	int deleteBoard(int post_id);	//게시물삭제
	int deleteRelply(int reply_id); //리플 삭제
	
}
