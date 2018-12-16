package com.digou.net;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CookieCheckInterceptor implements HandlerInterceptor {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    	
    	Cookie[] cookies = request.getCookies();
    	if(cookies == null || cookies.length == 0) {
        	Map<String, Object> objc = new HashMap<String, Object>();
        	objc.put("code", 103);
        	objc.put("data", null);
        	CookieCheckInterceptor.sendJsonMessage(response, objc);
    		return false;
    	}
//    	for(Cookie cookie : cookies){
//    		String cookieName = cookie.getName();
//    		if (cookieName.equals("username")) {
////				String username = cookie.getValue();
//    	    	System.out.println("@@@@@@@@@@@@@@@@@papapa");
//				return true;
//			}
//    	}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	System.out.println("^^^^^^^^post");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
    
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        String json = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        writer.print(json);
        writer.close();
        response.flushBuffer();
    }
}
