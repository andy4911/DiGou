package com.digou.controller;

import java.io.Console;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.digou.service.*;

import net.bytebuddy.asm.Advice.Return;

import com.digou.common.*;;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class AccountController {
    
    @Resource
    private AccountService accountService;
    
    @RequestMapping("/api/c/alterUserInfo")  
    public Map<String, Object> alterUserInfo(HttpServletResponse response, 
            @RequestParam(value = "userID", required = true) int userID,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "portraitURL", required = false) String portraitURL,
            @RequestParam(value = "address", required = false) String address) {
    	return accountService.alterUserInfo(response, userID, nickname, address, portraitURL);
    }
    
    @RequestMapping("/api/c/logup")  
    public Map<String, Object> logup(HttpServletResponse response, 
            @RequestParam(value = "username",required = true) String username,
            @RequestParam(value = "password",required = true) String password,
            @RequestParam(value = "address",required = true) String address,
            @RequestParam(value = "nickname",required = true) String nickname, 
            @RequestParam(value = "portraitURL",required = false, defaultValue = "") String portraitURL) {
    	if (!isllegal(username, password)) {
			return ResponseCommon.wrappedResponse(null, 105, null);
		}
        return accountService.logup(response, username, password, nickname, portraitURL, address);
    } 
    
    @RequestMapping("/api/c/login")  
    public Map<String, Object> login(HttpServletResponse response, 
            @RequestParam(value = "username",required = true) String username,
            @RequestParam(value = "password",required = true) String password) {
    	if (!isllegal(username, password)) {
			return ResponseCommon.wrappedResponse(null, 105, null);
		}
        return accountService.loginCheck(response, username, password);
    } 
    
    static boolean isllegal(String username, String password) {
    	//用户名密码判断逻辑
    	//以后补充
    	if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			return false;
		}
    	return true;
    }
}