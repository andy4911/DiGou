package com.digou.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.digou.entity.User;
import com.digou.service.UserService;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class UserController {
    
    @Resource
    private UserService userService;
    
    @RequestMapping("/")  
    public String root(){  
        return "hello";
    } 
    
     @RequestMapping("/say")  
     public String say(){  
         return "say nihao";
     }  
    
    @RequestMapping("/find")
    public String find(){
        User user =  userService.find(18);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user","sadf");
        return "HelloWord"+"fasdf--"+user.getUsername()+"--"+user.getPassword();
    }
    
    
    @RequestMapping("/ajax")
    public String find1(){
        return "[''message':'123dfx']";
    }
    
//    public static void main(String[] args){
//        SpringApplication.run(UserController.class,args);
//    }
}
