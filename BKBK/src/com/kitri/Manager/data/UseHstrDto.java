package com.kitri.Manager.data;

import java.util.Date;

// 이용내역 Dto

public class UseHstrDto {

	private String memberId; // 회원ID
	private String payNum; // 결제번호
	private Date stTime; // 시작시간
	private Date eTime; // 종료시간
	private Date payDate; // 결제일자

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPayNum() {
		return payNum;
	}

	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}

	public Date getStTime() {
		return stTime;
	}

	public void setStTime(Date stTime) {
		this.stTime = stTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

}
