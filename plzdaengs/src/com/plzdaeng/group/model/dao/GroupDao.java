package com.plzdaeng.group.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.plzdaeng.group.model.*;

public interface GroupDao {

	int createGroup(GroupDto dto);
	
	void applyGroupCategory(Connection con, GroupDto dto) throws SQLException;
	
	int changeGroup(GroupDto dto);
	
	int deleteGroup(int group_id);
	
	int deleteGroupmember(Connection conn, int group_id) throws SQLException;
	
	List<GroupDto> myGroup(String user_id);
	
	List<GroupDto> recommendGroup(String user_id);
	
	List<GroupDto> searchGroup(String key, String word);
	
	String inquiry(int group_id, String user_id);
	
	List<GroupBoard> boardLoading(int group_id);
	
	GroupDto groupDetail(int group_id);
	
	int joinGroup(int group_id, String id);
	
	int goOutGroup(int group_id, String id);
	
	List<GroupMember> memberlist(int group_id);
	
	int kickMember(GroupMember member);
	
	int permitMember(GroupMember member);
	
	int passAuthority(Connection conn, GroupMember member) throws SQLException;
	
	int removeAuthority(GroupMember member);
	
	GroupDto groupPageLoading(int group_id);
}
