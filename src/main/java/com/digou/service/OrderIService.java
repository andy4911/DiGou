package com.digou.service;

import com.digou.entity.Order;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public interface OrderIService {
	
	public Map<String, Object> makeOrder(int pID, int cID, int amount);
	
	public Map<String, Object> lookupOrders(int cID);

	public Map<String, Object> refund(int orderID);
	
	public Map<String, Object> ordersWithin(int cID, int type);

	public Map<String,Object> searchOrderByTime(HttpServletResponse response, int cid, long start, long end);

}
