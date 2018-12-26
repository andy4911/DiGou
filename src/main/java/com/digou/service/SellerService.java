package com.digou.service;

import com.digou.common.ResponseCommon;
import com.digou.entity.Order;
import com.digou.entity.Product;
import com.digou.entity.SellerUser;
import com.digou.mapper.SellerMapper;
import org.omg.CORBA.BAD_INV_ORDER;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ComponentScan({"com.digou.mapper"})
@Service("sellerService")
public class SellerService {
	
    @Resource
    private SellerMapper sellerMapper;

	public Map<String, Object> register(HttpServletResponse response,
										String url,
										String telephone,
										String shopName,
										String major,
										String description,
										String nickname,
										String password) {
		//检查
    	if (sellerMapper.findUser(telephone) != null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		}
		//插入
    	SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, password);
    	int result = sellerMapper.insertUser(user);
    	if (result != 1) {
			return ResponseCommon.wrappedResponse(null, 106, null);
		}
		//返回
    	Map<String, Object> data = new HashMap<>();
    	data.put("id", user.getId());
    	return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> info_get(HttpServletResponse response, int id) {
		//检查
		SellerUser sellerUser = sellerMapper.findUserById(id);
    	if (sellerUser == null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		} else {
			Map<String, Object> data = new HashMap<>();
			data.put("data", sellerUser);
			return ResponseCommon.wrappedResponse(data, 101, null);
		}
    }

	public Map<String, Object> info_modify(HttpServletResponse response, int id,String url,String telephone,String shopName,String major,String description,String nickname) {
		//插入
		SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, "");
		user.setId(id);
		sellerMapper.modifyUser(user);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	public Map<String, Object> good_add(HttpServletResponse response, int id, String url, String goodName, float price, String description, int num) {
		//插入
		Product product = new Product(goodName, description, price, url, id, num);
		System.out.println(num);
		sellerMapper.addGood(product);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	public Map<String, Object> good_all(HttpServletResponse response, int id) {
		//插入
		ArrayList<Product> products = sellerMapper.allGood(id);
		Map<String, Object> data = new HashMap<>();
		data.put("array", products);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}

	public Map<String, Object> login(HttpServletResponse response, String telephone, String password) {
		SellerUser user = sellerMapper.findUser(telephone);
		if (user == null || !user.getPassword().equals(password) || user.getIsBlack() == 1 || user.getIsPass() == 0) {
			return ResponseCommon.wrappedResponse(null, 102, null);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("data", user);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}

	//郭伟明
		//查看订单
	public Map<String, Object> look_order(HttpServletResponse response, int id) {
		//插入
		ArrayList<Order> orders = sellerMapper.allOrder(id);
		Map<String, Object> data = new HashMap<>();
		data.put("order", orders);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}
		//退款
	public Map<String, Object> refund_order(HttpServletResponse response, int orderId) {
		sellerMapper.refund(orderId);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

		//修改商品信息
		public Map<String, Object> good_modify(HttpServletResponse response,
											   int pID,
											   String pName, String description, float price, String portraitURL, int sID, int num) {
			Product product = new Product(  pName,  description,  price,  portraitURL,  sID,  num);
			product.setPId(pID);
			sellerMapper.modify(product);
			return ResponseCommon.wrappedResponse(null, 101, null);
		}
	//
}


