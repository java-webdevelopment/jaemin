package com.nucpoop.banking.dto;

public class DealDto {
	private int userKey;
	private String dealType;
	private int price;
	
	public DealDto() {
		
	}
	
	public DealDto(int userKey, String dealType, int price) {
		this.userKey = userKey;
		this.dealType = dealType;
		this.price = price;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
