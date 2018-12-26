package com.digou.entity;

public class Order extends BaseEntity {
	public int orderID;
	public int cID;//customerID
	public int pID;//productID
	public long createTime;
	public float orderPrice;
	//public int  isFinissh;
}
