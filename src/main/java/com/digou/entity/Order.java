package com.digou.entity;

public class Order extends BaseEntity {
    public int orderID;
    public int cID;//customerID
    public int pID;//productID
    public long createTime;
    public float orderPrice;
    //public int isFinish;
    public int amount;
    public Product product;
    public float adminProfit;
    public int commented;


    //gwm以下
    public int sID;//
    public String pName;
    public String Date;
    public String portraitURL;
    public String username;
    public String address;
    public String nickname;
    public String shopName;
    public String company;
    public String point;

    /** isFinissh
     * 1 未发货
     * 0 订单失败（退款）
     * 3 已发货
     * 2 订单完成（交易成功）
     * 4 已下单，未付款
     */
    public int   isFinish;
    /** isDelete
     * 0 未下架
     * 1 已下架
     * 不需要告诉前端，所以注释掉
     */
    //public int isDelete;//判断下架
    //gwm以上

}
