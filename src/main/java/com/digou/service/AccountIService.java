package com.digou.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface AccountIService {
	
	public Map<String, Object> alterUserInfo(HttpServletResponse response, 
															 int userID, 
													      String nickname, 
													      String address,
													      String portraitURL);
	public Map<String, Object> logup(HttpServletResponse response, 
												  String username, 
												  String password,
												  String nickname,
												  String portraitURL,
												  String address);
	public Map<String, Object> loginCheck(HttpServletResponse response, String userName, String password);
	
}
