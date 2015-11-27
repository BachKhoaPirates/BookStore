package com.bkpirates.entity;

public class BookEntity {
	private String bid;
	private String name;
	private int price;
	private int quantity;
	private String author;
	private String pulisher;
	private String content;
	private String linkImage;
	private int like;
	private int numberBookToBuy;
	public int getNumberBookToBuy() {
		return numberBookToBuy;
	}

	public void setNumberBookToBuy(int numberBookToBuy) {
		this.numberBookToBuy = numberBookToBuy;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public static final int IMAGE_SIZE = 200;

	public BookEntity() {}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPulisher() {
		return pulisher;
	}

	public void setPulisher(String pulisher) {
		this.pulisher = pulisher;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	};

	

}
