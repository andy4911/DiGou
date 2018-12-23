package com.digou.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface SellerAccountIService {
	Map<String, Object> register(HttpServletResponse response,
										String url,
										String telephone,
										String shopName,
										String major,
										String description,
										String nickname,
										String password);
}
