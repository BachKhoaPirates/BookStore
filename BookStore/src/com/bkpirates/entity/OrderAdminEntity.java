package com.bkpirates.entity;

public class OrderAdminEntity {
	
	private String oid;
	private String date;
	private String totalMoney;
	private String orderPerson;
	private String orderPersonAddress;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getOrderPerson() {
		return orderPerson;
	}
	public void setOrderPerson(String orderPerson) {
		this.orderPerson = orderPerson;
	}
	public String getOrderPersonAddress() {
		return orderPersonAddress;
	}
	public void setOrderPersonAddress(String orderPersonAddress) {
		this.orderPersonAddress = orderPersonAddress;
	}
}
