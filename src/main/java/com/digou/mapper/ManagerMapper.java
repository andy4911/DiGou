package com.digou.mapper;

import com.digou.entity.CUser;
import com.digou.entity.Order;
import com.digou.entity.SellerUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ManagerMapper {
	
	ArrayList<SellerUser> sellerPassInfo();
	ArrayList<SellerUser> sellerBlackInfo();
	ArrayList<SellerUser> sellerWhiteInfo(String value);

	ArrayList<CUser> customerBlackInfo();
	ArrayList<CUser> managerSearchCustomer(String value);

	ArrayList<Order> allOrder();
	ArrayList<Order> dayOrder(long start ,long end);
	ArrayList<Order> monthOrder(long start ,long end);
	ArrayList<Order> yearOrder(long start ,long end);
	ArrayList<Order> weekOrder(long start ,long end);
	ArrayList<Order>  searchOderByID(int orderID);


	void customerBlackCancel(int id);
	void customerWhiteBlock(int id);

	void sellerBlackCancel(int id);
	void sellerWhiteBlock(int id);
	void sellerUpdateApprove(int id);
	void sellerUpdateReject(int id);
//	int insertUser(SellerUser user);
//	SellerUser findUserById(int id);
//	void modifyUser(SellerUser user);
//	public CUser findCUserByID(int userID);
//	public void updateCUser(CUser account);
//	public int insertCUser(CUser account);
	
}
