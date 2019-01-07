package com.digou.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digou.service.AccountService;
import com.digou.service.OrderService;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class OrderController {
    
    @Resource
    private OrderService orderService;
    
    @RequestMapping("/api/c/makeOrder")  
    public Map<String, Object> makeOrder(HttpServletResponse response, 
            @RequestParam(value = "cID", required = true) int cID,
            @RequestParam(value = "pID", required = true) int pID, 
            @RequestParam(value = "amount", required = false, defaultValue = "1") int amount){
    	return orderService.makeOrder(pID, cID, amount);
    } 
    
    @RequestMapping("/api/c/lookupOrders")  
    public Map<String, Object> lookupOrders(HttpServletResponse response, 
            @RequestParam(value = "cID", required = true) int cID){
    	return orderService.lookupOrders(cID);
    } 

    @RequestMapping("/api/c/ordersWithin")  
    public Map<String, Object> ordersWithin(HttpServletResponse response, 
            @RequestParam(value = "cID", required = true) int cID, 
            @RequestParam(value = "type", required = true) int type) {
    	return orderService.ordersWithin(cID, type);
    } 
    
    @RequestMapping("/api/c/refund")  
    public Map<String, Object> refund(HttpServletResponse response, 
            @RequestParam(value = "orderID", required = true) int orderID){
    	return orderService.refund(orderID);
    } 

    @RequestMapping("/api/c/confirmOrder")  
    public Map<String, Object> confirm(HttpServletResponse response, 
            @RequestParam(value = "orderID", required = true) int orderID){
    	return orderService.confirm(orderID);
    }
    @RequestMapping("/api/c/search_order_by_time")
    public Map<String,Object> searchOrderByTime(HttpServletResponse response,
                                                @RequestParam(value  = "cid",required = true) int cid ,
                                                @RequestParam(value  = "start",required = true) long start ,
                                                @RequestParam(value  = "end",required = true) long end ){
        return orderService.searchOrderByTime(response,cid,start,end);
    }
}