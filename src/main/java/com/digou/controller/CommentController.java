package com.digou.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digou.service.CommentService;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class CommentController {
    @Resource
    private CommentService commentService;
    
    @RequestMapping("/api/c/comment")  
    public Map<String, Object> comment(HttpServletResponse response, 
            @RequestParam(value = "orderID", required = true) int orderID,
            @RequestParam(value = "message", required = true) String message) {
    	return commentService.comment(response, orderID, message);
    }
    
    @RequestMapping("/api/c/lookupComments")  
    public Map<String, Object> lookupComments(HttpServletResponse response, 
            @RequestParam(value = "pID", required = true) int pID) {
    	return commentService.lookupComments(response, pID);
    }
}
