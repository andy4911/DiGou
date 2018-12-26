package com.digou.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface FavouriteIService {
	
	public Map<String, Object> addFavourite(HttpServletResponse response, int cID, int pID);
	
	public Map<String, Object> myFavourite(HttpServletResponse response, int cID);

}
