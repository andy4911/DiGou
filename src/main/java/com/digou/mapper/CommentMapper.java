package com.digou.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.Comment;

@Mapper
public interface CommentMapper {
	public int insert(Comment comment);
	public ArrayList<Comment> find(int pID);

}
