package com.digou.entity;

public class Order extends BaseEntity {
	public int orderID;
	public int cID;//customerID
	public int pID;//productID
	public int sID;//
	public String pName;
	public int amount;
	public long  createTime;
	public String Date;
	public float orderPrice;
	public String portraitURL;

	public float adminProfit;

	public String username;
	public String address;
	/** isFinissh
	 * 1 未发货
	 * 0 订单失败（退款）
	 * 3 已发货
	 * 2 订单完成（交易成功）
	 */
	public int   isFinissh;

	/** isDelete
	 * 0 未下架
	 * 1 已下架
	 * 不需要告诉前端，所以注释掉
	 */
	//public int isDelete;//判断下架

}
