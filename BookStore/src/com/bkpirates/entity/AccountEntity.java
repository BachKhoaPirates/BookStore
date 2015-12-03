package com.bkpirates.entity;

public class AccountEntity {
	private String phone;
	private String password;
	private String name;
	private String address;
	private int money;

	public AccountEntity() {

	}

	public AccountEntity(String Phone, String Password, String Name, String Address, int Money) {
		this.phone = Phone;
		this.password = Password;
		this.name = Name;
		this.address = Address;
		this.money = Money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
