package com.digou.service;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.digou.common.ResponseCommon;
import com.digou.entity.Comment;
import com.digou.mapper.CommentMapper;

@ComponentScan({"com.digou.mapper"})
@Service("commentService")
public class CommentService {
	@Resource
    private CommentMapper commentMapper;
	
    public Map<String, Object> comment(HttpServletResponse response, 
		     								           int orderID, 
		     								        String message) {
    	Comment comment = new Comment();
    	comment.orderID = orderID;
    	comment.message = message;
    	commentMapper.insert(comment);
    	return ResponseCommon.wrappedResponse(null, 101, null);
    }
    
    public Map<String, Object> lookupComments(HttpServletResponse response, int pID) {
    	ArrayList<Comment> comments = commentMapper.find(pID);
    	ArrayList<String> conditionArr = new ArrayList<>();
    	conditionArr.add("createTime");
    	conditionArr.add("nickname");
    	conditionArr.add("message");
    	
    	Map<String, Object> data = ResponseCommon.filter(comments, conditionArr, "comments");
    	return ResponseCommon.wrappedResponse(data, 101, null);
    }
    
}
