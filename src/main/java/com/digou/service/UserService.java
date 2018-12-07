package com.digou.service;

import javax.annotation.Resource;
import com.digou.service.UserIService;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digou.entity.*;
import com.digou.mapper.*;


@ComponentScan({"com.digou.mapper"})
@Service("userService")
public class UserService implements UserIService{

    @Resource
    private UserMapper userMapper;
    
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public User find(int id) {
        return userMapper.find(id);
    }
    
    public void delete(int id){
        userMapper.delete(id);
    }
}