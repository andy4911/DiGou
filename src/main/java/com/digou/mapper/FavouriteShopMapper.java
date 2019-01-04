package com.digou.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.SellerUser;

@Mapper
public interface FavouriteShopMapper {
	public int insert(int cID, int sID, long createTime);
	public ArrayList<SellerUser> find(int sID);
	public int delete(int cID, int sID);
}
