package com.digou.service;

import java.util.Map;

public interface OrderIService {
	
	public Map<String, Object> makeOrder(int pID, int cID, int amount);
	
	public Map<String, Object> lookupOrders(int cID);

	public Map<String, Object> refund(int orderID);
}
