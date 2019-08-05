package com.kitri.Main.voucher;

public class VoucherDto {
	private String name;
	private int price;
	private int sec;
	
	public VoucherDto(String name, int price, int sec) {
		super();
		this.name = name;
		this.price = price;
		this.sec = sec;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
