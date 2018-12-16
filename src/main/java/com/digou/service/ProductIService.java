package com.digou.service;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface ProductIService {

	public Map<String, Object> searchProducts(HttpServletResponse response, String pName, int pageIndex, int pageSize);
}
