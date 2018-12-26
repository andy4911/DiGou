package com.digou.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digou.entity.*;
import com.digou.mapper.*;
import com.digou.common.*;

@ComponentScan({"com.digou.mapper"})
@Service("favouriteService")
public class FavouriteService implements FavouriteIService {
	@Resource
	FavouriteMapper favouriteMapper;
	
	public Map<String, Object> addFavourite(HttpServletResponse response, int cID, int pID) {
		Favourite favourite = new Favourite();
		favourite.cID = cID;
		favourite.pID = pID;
		favourite.createTime = System.currentTimeMillis();
		int r = favouriteMapper.insert(favourite);
		if (r > 0) {
			return ResponseCommon.wrappedResponse(null, 101, null);
		}
		return ResponseCommon.wrappedResponse(null, 105, null);
	}
	
	public Map<String, Object> myFavourite(HttpServletResponse response, int cID) {
		ArrayList<Favourite> arr = favouriteMapper.findByCID(cID);
		Map<String, Object> data = new HashMap<>();
		data.put("favourites", arr);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}
	
	public Map<String, Object> unfavourite(HttpServletResponse response, int cID, int pID) {
		favouriteMapper.delete(cID, pID);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}
}
