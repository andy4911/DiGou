package com.digou.mapper;

import com.digou.entity.SellerUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerAccountMapper {
	
	SellerUser findUser(String telephone);
	int insertUser(SellerUser user);
	SellerUser findUserById(int id);
	void modifyUser(SellerUser user);
//	public CUser findCUserByID(int userID);
//	public void updateCUser(CUser account);
//	public int insertCUser(CUser account);
	
}
