package com.plzdaeng.group.model;

import java.util.List;

public class GroupDto {

	private int group_id;
	private String group_leader;
	private String group_name;
	private String group_description;
	private String address_sido;
	
	private String group_img;
	private GroupCategory groupCategory;
	private List<GroupMember> groupMembers;

	// private

	public GroupDto() {
	}

	public GroupDto(int group_id, String group_leader, String group_name, String group_description, String address_sido,
			String group_img, GroupCategory groupCategory, List<GroupMember> groupMembers) {
		super();
		this.group_id = group_id;
		this.group_leader = group_leader;
		this.group_name = group_name;
		this.group_description = group_description;
		this.address_sido = address_sido;
		this.group_img = group_img;
		this.groupCategory = groupCategory;
		this.groupMembers = groupMembers;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_leader() {
		return group_leader;
	}

	public void setGroup_leader(String group_leader) {
		this.group_leader = group_leader;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_description() {
		return group_description;
	}

	public void setGroup_description(String group_description) {
		this.group_description = group_description;
	}

	public String getAddress_sido() {
		return address_sido;
	}

	public void setAddress_sido(String address_sido) {
		this.address_sido = address_sido;
	}

	public String getGroup_img() {
		return group_img;
	}

	public void setGroup_img(String group_img) {
		this.group_img = group_img;
	}

	public GroupCategory getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(GroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}

	public List<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	@Override
	public String toString() {
		return "GroupDto [group_id=" + group_id + ", group_leader=" + group_leader + ", group_name=" + group_name
				+ ", group_description=" + group_description + ", address_sido=" + address_sido + ", group_img="
				+ group_img + ", groupCategory=" + groupCategory + ", groupMembers=" + groupMembers + "]";
	}

	
	


	

}
