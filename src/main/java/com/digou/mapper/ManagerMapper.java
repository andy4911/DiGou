package com.digou.mapper;

import com.digou.entity.CUser;
import com.digou.entity.Order;
import com.digou.entity.Product;
import com.digou.entity.SellerUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ManagerMapper {
	
	ArrayList<SellerUser> sellerPassInfo();
	ArrayList<SellerUser> sellerBlackInfo();
	ArrayList<SellerUser> sellerWhiteInfo(String value);
	ArrayList<SellerUser> allSellerInfo();


	ArrayList<CUser> customerBlackInfo();
	ArrayList<CUser> managerSearchCustomer(String value);

	ArrayList<Order> allOrder();
//	ArrayList<Order> dayOrder(long start ,long end);
//	ArrayList<Order> monthOrder(long start ,long end);
//	ArrayList<Order> yearOrder(long start ,long end);
//	ArrayList<Order> weekOrder(long start ,long end);
	ArrayList<Order>  searchOrderByID(int orderID);
	ArrayList<Order>  searchOrderByTime(long start,long end);

	ArrayList<SellerUser> sellerTop5Info();
	ArrayList<Product> productTop10Info();

	void sellerToTop5(int id);
	void sellerTop5Cancel(int id);
	void productToTop10(int id);
	void productTop10Cancel(int id);

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
