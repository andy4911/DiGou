package com.digou.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digou.service.AccountService;
import com.digou.service.FavouriteService;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class FavouriteController {
	
    @Resource
    private FavouriteService favouriteService;
    
    @RequestMapping("/api/c/addFavourite")  
    public Map<String, Object> addFavourite(HttpServletResponse response, 
            @RequestParam(value = "cID", required = true) int cID,
            @RequestParam(value = "pID", required = true) int pID) {
    	return favouriteService.addFavourite(response, cID, pID);
    }
    
    @RequestMapping("/api/c/myFavourite")  
    public Map<String, Object> myFavourite(HttpServletResponse response, 
            @RequestParam(value = "cID", required = true) int cID) {
    	return favouriteService.myFavourite(response, cID);
    }
    
    @RequestMapping("/api/c/unfavourite")  
    public Map<String, Object> unfavourite(HttpServletResponse response, 
            @RequestParam(value = "cID", required = true) int cID, 
            @RequestParam(value = "pID", required = true) int pID) {
    	return favouriteService.unfavourite(response, cID, pID);
    }
}
