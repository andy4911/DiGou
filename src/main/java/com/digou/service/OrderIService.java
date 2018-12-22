package com.digou.service;

import java.util.Map;

public interface OrderIService {
	
	public Map<String, Object> makeOrder(int pID, int cID);
	
	public Map<String, Object> lookupOrders(int cID);

}
