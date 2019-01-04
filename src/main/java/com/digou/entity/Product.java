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
	public long createTime;
	public int isFinish;
	public int number = 1;
	public int orderID;
	public int isTop10;
/*	isTop10  -2:买家申请该商品为Top10，已经被管理员拒绝
	*		 -1：买家正在申请该商品为Top10，管理员还没处理
	*		 0：默认状态
	*		 1：管理员已经同意买家申请，设置为Top10，或者管理员主动把某商品设置成Top10
 */

	public Product(){
    }

	public Product(String pName, String description, float price, String portraitURL, int sID, int num) {
		this.pName = pName;
		this.description = description;
		this.price = price;
		this.portraitURL = portraitURL;
		this.sID = sID;
		this.num = num;
		this.isTop10 =0;
	}
	public void setPId(int pid) {
		this.pID = pid;
	}
}
