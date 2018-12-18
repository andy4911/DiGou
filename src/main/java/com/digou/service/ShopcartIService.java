package com.digou.service;

import java.util.ArrayList;
import java.util.Map;

import com.digou.entity.Product;

public interface ShopcartIService {
	
	public Map<String, Object> addToCard(int cID, int pID);
	public Map<String, Object> lookupCard(int cID);
	public ArrayList<Product> test(int cID);
}
