package com.digou.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.Order;

@Mapper
public interface OrderMapper {

	public int insert(Order order);
	
	public ArrayList<Order> find(int cID);

	public int updateStatus(int status, int orderID);
}

