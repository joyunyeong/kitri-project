package com.kitri.Food.data;

import java.util.Date;

// 회원 Dto

public class MemberDto {

	private String memberId; // 회원ID
	private String name; // 이름
	private String phoneNum1; // 전화번호1
	private String PhoneNum2; // 전화번호2
	private String PhoneNum3; // 전화번호3
	private String address; // 주소
	private Date birth; // 생년월일
	private int paySum; // 결제금액_누적
	private int couBirth; // 생일쿠폰
	private int couSale; // 천원할인쿠폰
	private char state; // 상태

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

	public String getPhoneNum1() {
		return phoneNum1;
	}

	public void setPhoneNum1(String phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}

	public String getPhoneNum2() {
		return PhoneNum2;
	}

	public void setPhoneNum2(String phoneNum2) {
		PhoneNum2 = phoneNum2;
	}

	public String getPhoneNum3() {
		return PhoneNum3;
	}

	public void setPhoneNum3(String phoneNum3) {
		PhoneNum3 = phoneNum3;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getPaySum() {
		return paySum;
	}

	public void setPaySum(int paySum) {
		this.paySum = paySum;
	}

	public int getCouBirth() {
		return couBirth;
	}

	public void setCouBirth(int couBirth) {
		this.couBirth = couBirth;
	}

	public int getCouSale() {
		return couSale;
	}

	public void setCouSale(int couSale) {
		this.couSale = couSale;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}

}
