package com.digou.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.*;

@Mapper
public interface LoginMapper {
	
	public Account find(String username);
	public void update(Account account);
}
