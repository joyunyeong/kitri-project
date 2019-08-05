package com.kitri.Main.memberDto;
/*회원ID	Member_ID
이름	Name
전화번호1	Phone_num1
전화번호2	Phone_num2
전화번호3	Phone_num3
주소	Address
생년월일	Birth
결제금액_누적	Pay_sum
생일쿠폰	Cou_birth
천원할인쿠폰	Cou_sale

 * ID Member_ID PK 이름+전화번호 마지막 4자리 이름 Name 전화번호 Phone_num 주소 Address 생년월일 Birth 결제금액_누적 Pay_sum 생일쿠폰 Cou_birth 천원할인쿠폰 Cou_sale 
 */
public class MemberDto {
	private String Member_ID = "";
	private String Name = "";
	private String Phone_Num1 = "";
	private String Phone_Num2="";
	private String Phone_Num3="";
	private String address="";
	String Birth="";
	int Pay_Sum=0;//누적요금
	int Cou_Birth=0;//생일쿠폰
	int Cou_Sale=0;// 천원할인쿠폰
	

	
		
	public MemberDto(String name, String phone_Num1, String phone_Num2, String phone_Num3, String address,
			String birth, int pay_Sum, int cou_Birth, int cou_Sale) {
		super();
		Member_ID = "";
		Name = name;
		Phone_Num1 = phone_Num1;
		Phone_Num2 = phone_Num2;
		Phone_Num3 = phone_Num3;
		this.address = address;
		Birth = birth;
		Pay_Sum = pay_Sum;
		Cou_Birth = cou_Birth;
		Cou_Sale = cou_Sale;
	}




	public String getMember_ID() {
		return Member_ID;
	}

	public void setMember_ID(String member_ID) {
		Member_ID = member_ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone_Num1() {
		return Phone_Num1;
	}

	public void setPhone_Num1(String phone_Num1) {
		Phone_Num1 = phone_Num1;
	}

	public String getPhone_Num2() {
		return Phone_Num2;
	}

	public void setPhone_Num2(String phone_Num2) {
		Phone_Num2 = phone_Num2;
	}

	public String getPhone_Num3() {
		return Phone_Num3;
	}

	public void setPhone_Num3(String phone_Num3) {
		Phone_Num3 = phone_Num3;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirth() {
		return Birth;
	}

	public void setBirth(String birth) {
		Birth = birth;
	}

	public int getPay_Sum() {
		return Pay_Sum;
	}

	public void setPay_Sum(int pay_Sum) {
		Pay_Sum = pay_Sum;
	}

	public int getCou_Birth() {
		return Cou_Birth;
	}

	public void setCou_Birth(int cou_Birth) {
		Cou_Birth = cou_Birth;
	}

	public int getCou_Sale() {
		return Cou_Sale;
	}

	public void setCou_Sale(int cou_Sale) {
		Cou_Sale = cou_Sale;
	}
	
	
}
