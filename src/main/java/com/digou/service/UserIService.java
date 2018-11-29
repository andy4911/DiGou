package com.digou.service;

import org.springframework.stereotype.Component;

import com.digou.entity.User;;;

public interface UserIService {
    
    public void insert(User user);
    public void update(User user);

    public User find(int id);
  
    public void delete(int id);
}
