package com.digou.mapper;

import com.digou.entity.SellerUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CommonMapper {
	
	ArrayList<SellerUser> commonSearchShop(String value);
//	int insertUser(SellerUser user);
//	SellerUser findUserById(int id);
//	void modifyUser(SellerUser user);
//	public CUser findCUserByID(int userID);
//	public void updateCUser(CUser account);
//	public int insertCUser(CUser account);
	
}
