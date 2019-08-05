package com.kitri.Statistics;

public class StatFoodDto {
	
	private int ranking;
//	private String food_num;
	private String food_ctg;
	private String food_name;
	private int food_price;
	private int order_amt;
	private int sum;
	
	//그래프용
	//상품 매출 top5
	private String productName;
	private int totalSellPrice;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getTotalSellPrice() {
		return totalSellPrice;
	}
	public void setTotalSellPrice(int totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}
	
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
//	public String getFood_num() {
//		return food_num;
//	}
//	public void setFood_num(String food_num) {
//		this.food_num = food_num;
//	}
	public String getFood_ctg() {
		return food_ctg;
	}
	public void setFood_ctg(String food_ctg) {
		this.food_ctg = food_ctg;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getFood_price() {
		return food_price;
	}
	public void setFood_price(int food_price) {
		this.food_price = food_price;
	}
	public int getOrder_amt() {
		return order_amt;
	}
	public void setOrder_amt(int order_amt) {
		this.order_amt = order_amt;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}


	
	

}
