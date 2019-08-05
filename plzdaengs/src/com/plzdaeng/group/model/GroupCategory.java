package com.plzdaeng.group.model;

public class GroupCategory {

	private String group_category_id;
	private String group_category_name;
	
	public GroupCategory() {
	}

	public GroupCategory(String group_category_id, String group_category_name) {
		super();
		this.group_category_id = group_category_id;
		this.group_category_name = group_category_name;
	}
	
	public String getGroup_category_id() {
		return group_category_id;
	}

	public void setGroup_category_id(String group_category_id) {
		this.group_category_id = group_category_id;
	}

	public String getGroup_category_name() {
		return group_category_name;
	}

	public void setGroup_category_name(String group_category_name) {
		this.group_category_name = group_category_name;
	}

	@Override
	public String toString() {
		return "GroupCategory [group_category_id=" + group_category_id + ", group_category_name=" + group_category_name
				+ "]";
	}

	
	
	
}
