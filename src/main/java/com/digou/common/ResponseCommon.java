package com.digou.common;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;

import org.apache.commons.collections.map.HashedMap;

import com.digou.entity.*;

public class ResponseCommon {
	
	static public Map<String, Object> filter(BaseEntity entity, ArrayList<String> conditionArr) {
		Map<String, Object> data = new HashMap<String, Object>();
		Iterator it = conditionArr.iterator();
		try {
	        while(it.hasNext()) {
	        	String condition = (String)it.next();
	        	Field field = entity.getClass().getDeclaredField(condition);
	        	Object o = field.get(entity);
		        data.put(condition, field.get(entity));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			// who cares?
		}
        return data;
	}
	
	static public Map<String, Object> filter(ArrayList entityArr, ArrayList<String> conditionArr, String arrKey) {
		Iterator it = entityArr.iterator();
		ArrayList arr = new ArrayList<>();
        while(it.hasNext()) {
        	BaseEntity entity = (BaseEntity)it.next();
        	Map<String, Object> i = filter(entity, conditionArr);
        	arr.add(i);
        }
        
		Map<String, Object> data = new HashMap<String, Object>();
        data.put(arrKey, arr);
        return data;
	}
	
//	static public Map<String, Object> initResponse(Object o, ArrayList<String> conditionArr, int code) {
//		Map<String, Object> response = new HashMap<String, Object>();
//		Map<String, Object> data = new HashMap<String, Object>();
//		if (o instanceof Collection) {
//			ArrayList<Map> arr = filter((ArrayList<BaseEntity>)o, conditionArr);
//			data.put("array", data);
//		} else if (o instanceof BaseEntity) {
//			Map<String, Object> data = filter((BaseEntity)o, conditionArr);
//			response.put("data", data);
//		}
//		response.put("code", code);
//		return response;
//	}
	
	static public Map<String, Object> wrappedResponse(Map<String, Object> data, int code, Map<String, Object> map) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", code);
		if (data == null && map == null) {
			return response;
		}
		
		if (map != null) {
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()) {
				String key = it.next();
				data.put(key, map.get(key));
			}
		}
		response.put("data", data);
		return response;
	}

}
