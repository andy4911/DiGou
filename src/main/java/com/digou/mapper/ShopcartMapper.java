package com.digou.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.Product;

@Mapper
public interface ShopcartMapper {
	public int insert(int cID, int pID, long createTime);
	public ArrayList<Map> find(Integer cID, Integer pID);
	public ArrayList<Product> findProductsInCart(int cID);
	public int delete(int cID, int pID);
}
