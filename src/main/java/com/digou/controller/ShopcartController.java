package com.digou.controller;

import java.io.Console;
import java.util.ArrayList;
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
import com.digou.common.*;
import com.digou.entity.Product;;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class ShopcartController {
    
    @Resource
    private ShopcartService shopcartService;
    
    @RequestMapping("/api/c/addToCart")  
    public Map<String, Object> addToShopcart(HttpServletResponse response, 
            @RequestParam(value = "cID",required = true) int cID,
            @RequestParam(value = "pID",required = true) int pID) {
    	return shopcartService.addToCard(cID, pID);
    } 
    
    @RequestMapping("api/c/lookupCart")  
    public Map<String, Object> lookupShopcart(HttpServletResponse response, 
            @RequestParam(value = "cID",required = true) int cID) {
    	return shopcartService.lookupCard(cID);
    }
    
    @RequestMapping("api/c/test")  
    public ArrayList<Product> test(HttpServletResponse response, 
            @RequestParam(value = "cID",required = false) Integer cID, 
            @RequestParam(value = "pID",required = false) Integer pID) {
    	return shopcartService.test(cID);
    }
    
    
    @RequestMapping("api/c/removeFromCart")  
    public Map<String, Object> removeFromCart(HttpServletResponse response, 
            @RequestParam(value = "cID",required = true) Integer cID, 
            @RequestParam(value = "pID",required = true) Integer pID) {
    	return shopcartService.removeFromCart(cID, pID);
    }
}