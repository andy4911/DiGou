package com.digou.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.digou.service.UserIService;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digou.entity.*;
import com.digou.mapper.*;
import com.digou.common.*;

@ComponentScan({"com.digou.mapper"})
@Service("loginService")
public class LoginService implements LoginIService {
	
    @Resource
    private LoginMapper loginMapper;
	
    @Override
	public Map<String, Object> loginCheck(HttpServletResponse response, String username, String password) {
		Account account = loginMapper.find(username);
		
		LoginCheckEnum code;
		if(account == null) {
			code = LoginCheckEnum.USERNAME_ERROR;
		} else if(!account.password.equals(password)) {
			code = LoginCheckEnum.PASSWORD_ERROR;
		} else if (account.isOnline) {
			code = LoginCheckEnum.IS_ONLINE;
		} else {
			account.isOnline = true;
			code = LoginCheckEnum.SUCCESS;
		}
		loginMapper.update(account);
		
		this.addCookie(response, username);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("nickname", account.nickname);
		data.put("userID", account.userID);
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("code", code.value());
		resp.put("data", data);
		return resp;
	}
    
    private void addCookie(HttpServletResponse response, String username) {
    	Cookie nameCookie = new Cookie("username",username);
    	nameCookie.setMaxAge(3600);
    	nameCookie.setPath("/");
    	Cookie timeCookie = new Cookie("lastAccessTime", System.currentTimeMillis()+"");
    	timeCookie.setMaxAge(3600);
    	timeCookie.setPath("/");
        response.addCookie(nameCookie);
        response.addCookie(timeCookie);
	}
}
