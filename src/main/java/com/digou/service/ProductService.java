package com.digou.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digou.entity.*;
import com.digou.mapper.*;
import com.digou.common.*;

import javax.servlet.http.HttpServletResponse;

@ComponentScan({"com.digou.mapper"})
@Service("productService")
public class ProductService {
	
	@Resource
	ProductMapper productMapper;
	
	public Map<String, Object> searchProducts(HttpServletResponse response, String pName, int pageIndex, int pageSize) {
    	if (pageSize == 0) {
			pageSize = 20;
		} 
		
		ArrayList<Product> productArr = null;
		if (pName == null || pName.equals("")) {
			productArr = productMapper.findAll(pageIndex * pageSize, pageSize);
		} else {
			productArr = productMapper.find(pName, pageIndex * pageSize, pageSize);
		}
		
		ArrayList<String> conditionArr = new ArrayList<String>();
		conditionArr.add("pName");
		conditionArr.add("price");
		conditionArr.add("num");
		conditionArr.add("shopName");
		conditionArr.add("pID");
		conditionArr.add("description");
		conditionArr.add("portraitURL");
		
		Map<String, Object> data = ResponseCommon.filter(productArr, conditionArr, "pArray");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", pageIndex);
		return ResponseCommon.wrappedResponse(data, 101, map);
//=======
//	public Map<String, Object> searchProducts(HttpServletResponse response, String pName) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("array", productMapper.find(pName));
//		return ResponseCommon.wrappedResponse(map, 101, map);
//>>>>>>> Stashed changes
	}

}
