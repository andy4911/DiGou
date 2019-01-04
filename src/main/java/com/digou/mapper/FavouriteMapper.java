package com.digou.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.Favourite;

@Mapper
public interface FavouriteMapper {
	public int insert(Favourite favourite);
	public ArrayList<Favourite> findByCID(int cID);
	public int delete(int cID, int pID);
}
