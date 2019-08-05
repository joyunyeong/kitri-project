package com.kitri.Main.dto;

import com.kitri.Book.rent.BookDTO;
import com.kitri.Food.data.FoodDto;
import com.kitri.Main.voucher.VoucherDto;

public class Basket {
	
	private VoucherDto vdto;
	public FoodDto fdto;
	public BookDTO bdto;
	public int count;
	private String name;
	
	public Basket(VoucherDto vdto, int count) {
		super();
		this.vdto = vdto;
		this.count = count;
		this.name = vdto.toString();
	}
	public Basket(FoodDto fdto, int count) {
		super();
		this.fdto = fdto;
		this.count = count;
		this.name = fdto.toString();
	}
	public Basket(BookDTO bdto, int count) {
		super();
		this.bdto = bdto;
		this.count = count;
		this.name = bdto.toString();
	}
	
	
	
	public VoucherDto getVoucherDto() {
		return vdto;
	}



	public void setVoucherDto(VoucherDto vdto) {
		this.vdto = vdto;
	}

	public FoodDto getFoodDto() {
		return fdto;
	}



	public void setFoodDto(FoodDto fdto) {
		this.fdto = fdto;
	}



	public BookDTO getBookDto() {
		return bdto;
	}



	public void setBookDto(BookDTO bdto) {
		this.bdto = bdto;
	}
	

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return name;
	}
}
