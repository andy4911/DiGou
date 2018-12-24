package com.digou.service;

import com.digou.common.ResponseCommon;
import com.digou.entity.SellerUser;
import com.digou.mapper.SellerAccountMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ComponentScan({"com.digou.mapper"})
@Service("sellerAccountService")
public class SellerAccountService{
	
    @Resource
    private SellerAccountMapper sellerAccountMapper;

	public Map<String, Object> register(HttpServletResponse response,
										String url,
										String telephone,
										String shopName,
										String major,
										String description,
										String nickname,
										String password) {
		//检查
    	if (sellerAccountMapper.findUser(telephone) != null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		}
		//插入
    	SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, password);
    	int result = sellerAccountMapper.insertUser(user);
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
		SellerUser sellerUser = sellerAccountMapper.findUserById(id);
    	if (sellerUser == null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		} else {
			Map<String, Object> data = new HashMap<>();
			data.put("data", sellerUser);
			return ResponseCommon.wrappedResponse(data, 101, data);
		}
    }

	public Map<String, Object> info_modify(HttpServletResponse response, int id,String url,String telephone,String shopName,String major,String description,String nickname) {
		//插入
		SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, "");
		user.setId(id);
		sellerAccountMapper.modifyUser(user);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	public Map<String, Object> login(HttpServletResponse response, String telephone, String password) {
		SellerUser user = sellerAccountMapper.findUser(telephone);
		if (user == null || !user.getPassword().equals(password) || user.getIsBlack() == 1 || user.getIsPass() == 0) {
			return ResponseCommon.wrappedResponse(null, 102, null);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("data", user);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}
}
