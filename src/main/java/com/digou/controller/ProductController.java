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
import com.digou.common.*;;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class ProductController {
    
    @Resource
    private ProductService productService;
    
    @RequestMapping("/api/c/searchproducts")  
    public Map<String, Object> searchProducts(HttpServletResponse response, 
            @RequestParam(value = "pName", required = false) String pName) {
    	return productService.searchProducts(response, pName);
    } 
}