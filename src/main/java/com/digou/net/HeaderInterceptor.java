package com.digou.net;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HeaderInterceptor extends HandlerInterceptorAdapter {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		
		System.out.println("&&&&&&&&&**********");
    	
    	String origin = response.getHeader("Origin");
    	response.setHeader("Access-Control-Allow-Origin", "*");
//    	response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//    	response.setHeader("Access-Control-Allow-Headers","Origin, Content-Type, Accept, token, X-Requested-With");
//    	response.setHeader("Access-Control-Allow-Credentials", "true");
//    	response.setHeader("XDomainRequestAllowed", "1");
//    	response.setHeader("Access-Control-Max-Age", "3600");
    	
    	return true;
	}

}
