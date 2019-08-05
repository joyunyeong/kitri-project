package com.plzdaeng.dto;

public class UserDetailDto {
	private String tel;
	private String gender;
	private String zipcode;
	private String address;
	private String address_detail;
	
	public UserDetailDto() {
		super();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	@Override
	public String toString() {
		return "UserDetailDto [tel=" + tel + ", gender=" + gender + ", zipcode=" + zipcode + ", address=" + address
				+ ", address_detail=" + address_detail + "]";
	}	
}
