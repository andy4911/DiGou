package com.digou.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.digou.common.ResponseCommon;
import com.digou.entity.Order;
import com.digou.entity.Product;
import com.digou.mapper.AccountMapper;
import com.digou.mapper.OrderMapper;
import com.digou.mapper.ProductMapper;
import com.digou.mapper.ShopcartMapper;

import antlr.collections.impl.IntRange;

@ComponentScan({"com.digou.mapper"})
@Service("orderService")
public class OrderService implements OrderIService {
	
	@Resource
    private OrderMapper orderMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private ShopcartMapper cartMapper;
	
	public Map<String, Object> makeOrder(int pID, int cID, int amount) {
		Product product = productMapper.findByID(pID);
		if (product == null) {
			return ResponseCommon.wrappedResponse(null, 102, null);
		} else if (product.num < amount) {
			return ResponseCommon.wrappedResponse(null, 105, null);
		}
		
		productMapper.updateNum(product.num - amount, product.pID);
		Order order = new Order();
		order.createTime = System.currentTimeMillis();
		order.cID = cID;
		order.pID = pID;
		order.orderPrice = product.price;
		order.amount = amount;
		orderMapper.insert(order);
		cartMapper.delete(cID, pID);
		
		ArrayList<String> conditionArr = new ArrayList<String>();
		conditionArr.add("orderID");
		Map<String, Object> data = ResponseCommon.filter(order, conditionArr);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}
	
	public Map<String, Object> lookupOrders(int cID) {
		ArrayList<Order> orders = orderMapper.find(cID);
		
//		ArrayList<String> conditionArr = new ArrayList<String>();
//		conditionArr.add("orderID");
//		conditionArr.add("cID");
//		conditionArr.add("pID");
//		conditionArr.add("createTime");
//		conditionArr.add("orderPrice");
//		Map<String, Object> data = ResponseCommon.filter(orders, conditionArr, "orderArr");
		Map<String, Object> data = new HashMap<>();
		data.put("orderArr", orders);
//		ArrayList<Map> orderArr = (ArrayList<Map>)data.get("orderArr");
		
//		Iterator<Map> iterator = orderArr.iterator();
//		while(iterator.hasNext()) {
//			Map order = iterator.next();
//			System.out.println(order.get("orderID") + "#################");
//			Product product = productMapper.findByID((int)order.get("pID"));
//			order.put("product", product);	
//		}
		
		
		return ResponseCommon.wrappedResponse(data, 101, null);
	}

	public Map<String, Object> refund(int orderID) {
		int r = orderMapper.updateStatus(0, orderID);
		return ResponseCommon.wrappedResponse(null, 101, null);
	} 
}
