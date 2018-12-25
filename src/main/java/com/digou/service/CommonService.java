package com.digou.service;

import com.digou.common.ResponseCommon;
import com.digou.entity.SellerUser;
import com.digou.mapper.CommonMapper;
import com.digou.mapper.ManagerMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ComponentScan({"com.digou.mapper"})
@Service("commonService")
public class CommonService {
	
    @Resource
    private CommonMapper commonMapper;

	public Map<String, Object> commonSearchShop(HttpServletResponse response, String value) {
        ArrayList<SellerUser> sellerUsers = commonMapper.commonSearchShop(value);
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

}
