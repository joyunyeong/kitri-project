package com.plzdaeng.dto;

public class UserDto {
	private String user_id;
	private String password;
	private String emailid;
	private String emaildomain;
	private String nickname;
	private String user_img;
	
	//관리자 or 일반유저
	//관리자 : A(admin)  , 유저 : U(user)
	private String authority;
	
	//유저의 상세정보
	private UserDetailDto userDetailDto;

	public UserDto() {
		super();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getEmaildomain() {
		return emaildomain;
	}

	public void setEmaildomain(String emaildomain) {
		this.emaildomain = emaildomain;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public UserDetailDto getUserDetailDto() {
		return userDetailDto;
	}

	public void setUserDetailDto(UserDetailDto userDetailDto) {
		this.userDetailDto = userDetailDto;
	}

	@Override
	public String toString() {
		return "UserDto [user_id=" + user_id + ", password=" + password + ", emailid=" + emailid + ", emaildomain="
				+ emaildomain + ", nickname=" + nickname + ", user_img=" + user_img + ", authority=" + authority
				+ ", userDetailDto=" + userDetailDto + "]";
	}

}
