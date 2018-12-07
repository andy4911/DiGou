package com.digou.entity;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.apache.bcel.classfile.Field;

public class BaseEntity {
	
	public Map<String, Object> wrappedResponse(int code) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", code);
		response.put("data", this);
		return response;
	}	

}
