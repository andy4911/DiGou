package com.digou.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.digou.entity.Admin;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.digou.common.DateRange;
import com.digou.common.DateRange.BeginOrEnd;
import com.digou.common.DateRange.RangeType;
import com.digou.common.ResponseCommon;
import com.digou.entity.Order;
import com.digou.entity.Product;
import com.digou.mapper.AccountMapper;
import com.digou.mapper.OrderMapper;
import com.digou.mapper.ProductMapper;
import com.digou.mapper.ShopcartMapper;
import com.digou.mapper.ManagerMapper;

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
	@Resource
	private ManagerMapper managerMapper;
	
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
		order.orderPrice = product.price *amount;
		order.amount = amount;
		order.commented = 0;
		System.out.println(  managerMapper.profitRate());
		float rate  = (float ) managerMapper.profitRate();
		order.adminProfit =order.orderPrice * rate;
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
	
	public Map<String, Object> confirm(int orderID) {
		int r = orderMapper.updateStatus(2, orderID);
		return ResponseCommon.wrappedResponse(null, 101, null);
	} 
	
	public Map<String, Object> ordersWithin(int cID, int type) {
		ArrayList<Order> orders;
		switch (type) {
		case 1:
			orders = orderMapper.findWithinTime(cID, 
					DateRange.getTimeStamp(RangeType.YEAR, BeginOrEnd.BEGIN, new Date()),
					DateRange.getTimeStamp(RangeType.YEAR, BeginOrEnd.END, new Date()));
			break;
			
		case 2:
			orders = orderMapper.findWithinTime(cID, 
					DateRange.getTimeStamp(RangeType.MONTH, BeginOrEnd.BEGIN, new Date()),
					DateRange.getTimeStamp(RangeType.MONTH, BeginOrEnd.END, new Date()));
			break;
			
		case 3:
			orders = orderMapper.findWithinTime(cID, 
					DateRange.getTimeStamp(RangeType.DAY, BeginOrEnd.BEGIN, new Date()),
					DateRange.getTimeStamp(RangeType.DAY, BeginOrEnd.END, new Date()));
			break;
			
		case 4:
			orders = orderMapper.findWithinTime(cID, 
					DateRange.getTimeStamp(RangeType.WEEK, BeginOrEnd.BEGIN, new Date()),
					DateRange.getTimeStamp(RangeType.WEEK, BeginOrEnd.END, new Date()));
			break;

		default:
			return ResponseCommon.wrappedResponse(null, 103, null);
		}
		
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		
		ArrayList<String> conditionArr = new ArrayList<>();
		conditionArr.add("orderID");
		conditionArr.add("cID");
		conditionArr.add("pID");
		conditionArr.add("createTime");
		conditionArr.add("orderPrice");
		conditionArr.add("amount");
		conditionArr.add("product");
		conditionArr.add("commented");
		Map<String, Object> data = ResponseCommon.filter(orders, conditionArr, "orderArr");
		return ResponseCommon.wrappedResponse(data, 101, null);
	}


	public Map<String,Object> searchOrderByTime(HttpServletResponse response, int cid, long start, long end){
		ArrayList<Order> orders = orderMapper.searchOrderByTime(cid,start,end);
		Map<String, Object> data = new HashMap<>();
		data.put("array", orders);
		return ResponseCommon.wrappedResponse(data, 101, null);

	}

}
