package com.digou.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.*;

@Mapper
public interface ProductMapper {
	public ArrayList<Product> find(String pName);
	public ArrayList<Product> findAll(int origin, int length);
	public Product findByID(int pID);
}
