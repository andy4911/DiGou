package com.digou.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.digou.common.ResponseCommon;
import com.digou.entity.Product;
import com.digou.mapper.ShopcartMapper;


@ComponentScan({"com.digou.mapper"})
@Service("Service")
public class ShopcartService implements ShopcartIService {
	
	@Resource
	ShopcartMapper mapper;
	
	public Map<String, Object> addToCard(int cID, int pID) {
		ArrayList<Map> records = mapper.find(cID, pID);
		if(!records.isEmpty()) {
			return ResponseCommon.wrappedResponse(null, 102, null);
		}
		long createTime = System.currentTimeMillis();
		int r = mapper.insert(cID, pID, createTime);
		if (r < 1) {
			return ResponseCommon.wrappedResponse(null, 105, null);
		}
		return ResponseCommon.wrappedResponse(null, 101, null);
	}
	
	public Map<String, Object> lookupCard(int cID) {
		ArrayList<Product> productArr = mapper.findProductsInCart(cID);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("pArray", productArr);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}
	
	public Map<String, Object> removeFromCart(int cID, int pID) {
		mapper.delete(cID, pID);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}
	
	public ArrayList<Product> test(int cID) {
		return mapper.findProductsInCart(new Integer(cID));
	}
	
}
