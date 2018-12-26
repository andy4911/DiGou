package com.digou.mapper;

import com.digou.entity.Order;
import com.digou.entity.Product;
import com.digou.entity.SellerUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SellerMapper {
	
	SellerUser findUser(String telephone);
	int insertUser(SellerUser user);
	SellerUser findUserById(int id);
	void modifyUser(SellerUser user);
	void addGood(Product good);
	ArrayList<Product> allGood(int id);
//	public CUser findCUserByID(int userID);
//	public void updateCUser(CUser account);
//	public int insertCUser(CUser account);

	//gwm
	ArrayList<Order> allOrder(int id);
	void refund(int orderId);
	void modify(Product product);
}
