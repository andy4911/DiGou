package com.digou.entity;

import java.util.function.IntToDoubleFunction;

public class Product extends BaseEntity {
	public int pID;
	public String pName;
	public int sID;
	public String shopName;
	public String description;
	public int num;
	public float price;
	public String portraitURL;

	public Product(){

    }

	public Product(String pName, String description, float price, String portraitURL, int sID, int num) {
		this.pName = pName;
		this.description = description;
		this.price = price;
		this.portraitURL = portraitURL;
		this.sID = sID;
		this.num = num;
	}
}
