package com.kitri.Manager.data;

// 재고 Dto

public class StockDto {

	private String foodCtg; // 음식종류
	private String stockName; // 재고명
	private int restAmt; // 잔여수량
	private int unitCost; // 단가

	public String getFoodCtg() {
		return foodCtg;
	}

	public void setFoodCtg(String foodCtg) {
		this.foodCtg = foodCtg;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getRestAmt() {
		return restAmt;
	}

	public void setRestAmt(int restAmt) {
		this.restAmt = restAmt;
	}

	public int getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(int unitCost) {
		this.unitCost = unitCost;
	}

}
