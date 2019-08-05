package com.kitri.Statistics;

import java.util.Date;

//**************************************************************

public class StatMemberDto {
	private String age;        //나이대
	private String memberId;	//회원ID
	private String name;		//이름
	private String birth;			//생년월일
	
	//그래프용
	// 손님 연령별 그래프
	private String customerAge;
	private int customerAgeCount;

	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public Integer getStatTotalPrice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCustomerAge() {
		return customerAge;
	}
	public void setCustomerAge(String customerAge) {
		this.customerAge = customerAge;
	}
	public int getCustomerAgeCount() {
		return customerAgeCount;
	}
	public void setCustomerAgeCount(int customerAgeCount) {
		this.customerAgeCount = customerAgeCount;
	}

}
