package com.nucpoop.banking.dto;

public class UserDto {
	private int userKey;
	private String id;
	private String pw;
	private int balance;

	public UserDto() {

	}

	public UserDto(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
