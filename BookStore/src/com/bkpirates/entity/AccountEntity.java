package com.bkpirates.entity;

public class AccountEntity {
	private String phone;
	private String password;
	private String name;
	private String address;
	public AccountEntity(){
		
	}
	public AccountEntity(String Phone, String Password, String Name, String Address){
		this.phone = Phone;
		this.password = Password;
		this.name = Name;
		this.address = Address;
		
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
