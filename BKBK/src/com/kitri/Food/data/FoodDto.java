package com.kitri.Food.data;

// 음식 Dto

public class FoodDto {
	private String foodName; // 음식이름
	private String foodCtg; // 음식종류
	private int foodPrice; // 음식가격
	private String stock1Name; // 재고1
	private int stock1Num; // 재고1수량
	private String stock2Name; // 재고2
	private int stock2Num; // 재고2수량
	private String stock3Name; // 재고3
	private int stock3Num; // 재고3수량
	private char state; // 상태

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodCtg() {
		return foodCtg;
	}

	public void setFoodCtg(String foodCtg) {
		this.foodCtg = foodCtg;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getStock1Name() {
		return stock1Name;
	}

	public void setStock1Name(String stock1Name) {
		this.stock1Name = stock1Name;
	}

	public int getStock1Num() {
		return stock1Num;
	}

	public void setStock1Num(int stock1Num) {
		this.stock1Num = stock1Num;
	}

	public String getStock2Name() {
		return stock2Name;
	}

	public void setStock2Name(String stock2Name) {
		this.stock2Name = stock2Name;
	}

	public int getStock2Num() {
		return stock2Num;
	}

	public void setStock2Num(int stock2Num) {
		this.stock2Num = stock2Num;
	}

	public String getStock3Name() {
		return stock3Name;
	}

	public void setStock3Name(String stock3Name) {
		this.stock3Name = stock3Name;
	}

	public int getStock3Num() {
		return stock3Num;
	}

	public void setStock3Num(int stock3Num) {
		this.stock3Num = stock3Num;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return foodName;
	}
}
