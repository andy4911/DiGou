package com.digou.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.digou.common.ResponseCommon;
import com.digou.service.CommonService;
import com.digou.service.ManagerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class CommonController {
    @Resource
    private CommonService commonService;

    @RequestMapping("/api/common/search/shop")
    public Map<String, Object> commonSearchShop(HttpServletResponse response,
                                                @RequestParam(value = "value",required = true) String value) {
        return commonService.commonSearchShop(response, value);
    }

    @RequestMapping("/api/common/pay")
    public Map<String, Object> commonPay(HttpServletResponse response,
                                         @RequestParam(value = "name",required = true) String name,
                                         @RequestParam(value = "price",required = true) float price,
                                         @RequestParam(value = "count",required = true) int count,
                                         @RequestParam(value = "id",required = true) int id) {
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do", "2016092400586092",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCU5c0w8oLV3NbeEal+3Vj0x1ThWNYSC9WqQjnIt3zabV1bDC5E1Lz1OTsSKUCljXO/+qmaMfNBV3tUj4pY0JFkF0g6DnAmJG6wCj3ixOCPelAgQzFWMKtvu3sN20v6x5tpRuES2FLrRcls8/exQ0yQBZw1NTZlOM/eENO8vqOxjZPkaaBEAQ1ESqYMWl/G0HzE9bPMZxXcXDt4R4oeXc/H5NqJge+drcIPxVxpdmUlL6WuvsCPvBiqRlm/VvEJO/B1tzNP52MpMY7rUeow0xWzooBCZQm+agvoJHxwXhysUV+VERAob+56pZb9o0gDUnOWTO+PLxDKpEThU87f3edzAgMBAAECggEAP7gwHXhKwVbmpNYOIPp8rRP4oQ8xXTuvTlNVAy5ywc73mpUV3+LiklbRjq4UukRUep28ov4pSZc0cjWTUPrAnYLRJ/cxy0DgFXnsjfUO3JfNXmEi5e2iEAi9Wmq0kzeErILmovfsVD7jOjsVjshsJV4hQSvqgVrUqgWtdyJmTv7IVSOH9alN1xfzqyUTeThpWGkCnaeJya1pPIUEanAVlctrRuJSTLqVMSk6eryMYP9Y3CBFbzH95elOPCUyr/LGy6w/A529+Hfeic58T82q22xt6mnT0Ch2eWWM2QRYecuZFuHDaqzINVVGgbQg4w9krYvWUdIWajShv32QzNJKkQKBgQD/be8iDVIksPVa7MnfH8A5T7VlDf1vCj6Cg5YLAA8oltSqVqJBWGsl6o8BJL72Z+EupHMvo6QojWdtPOIAhaRPJvGo8YjmG8w0Lq8OISbXEH8QVKgeS9qqNJtFd1eGorABYK0iRd1C+3AuGM47hD2+XjFn+AKNliQwLoPXtE1JpwKBgQCVOvKkZMkg1gXydqfdPi95wuO5NIHv1SK3WZclH2XkdX6pgVcsAaIIiCw6Vd4WiC9ScTqXoIB10Y4RZCOjoPPQkVKPR9pWzf7I27wNKp5T6dlvPtm5pWGp3hJUzCRrqjBSCr3DTwdC2qNUM4Iy7p7soo1M8CFbKugQ3RlQeUBVVQKBgQCKuCGO1L0vwAr//mkEDDJQ7cENyLNCgsXAC8aI4tdYq9GDUNl9s489YE2ZyWMMXw4kTptf2/djLqhk4xO6RNofQn8ryUEX9zDHhjL78XUuh376V3+WNIvks/0/lLisHd6IYQCmBFtN7U/XORGHsSH3KSCb3k1Ep62vnRNtKjZvMQKBgHBKNP6in/1HTp5Nzq5Z9ptmnLhl3Eg9Vqwki+jxIVixWKfvnUqNyAEnhjA3ILBPtVwRu4cQzNja4E4Rzgd4Oz57u2X5KUAcLVWykmRN52k5E9Xzsx1QZpbNdXm9SsKBA28YigYG123k2vWm9Jeimo9xGWYFYvQY6xqVZFD7i/8pAoGAdorOnCJ7Nllk+j9g7vfbxoriYGCuBQGDrbZdUUI0O9XNCSjQ5RP2Dq50tPqeNwL+WNfxT85MHuzMgZXPq/8BjKuBiLJCBcdbYiVYLCFurXy73nlyt4BQrmUvdJhUrq4J0bc8yTYKyEs0xmJcvbD+51FRfREqZ5efR2a0AYoe9fg=",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4gYmIFSJbdQIc9OdTEGceNw5Bsw8GNDb0/B7qqgdWbFIQgARn/GtPFZKQAyREMQ/BV3sxQ7l1jXttDt3agfwtfFZsA6MV9VJZaYUBxwxg4LGtgDPgUf/d1ejGrnOtUkWbSJ/OnH9D4CdVwpUg8rt3tuAQuldE+6lV2CbSUDrwqgMyTk9Q9o3yIXzKuwlRe9kZZN3vuErPavaElD1unbUFUon7FKyfLdG+/DUghubfkWgpolJPCTa5DouuxgAFAcGd+hvE42Gju2muT3CyuIwIr1RBGFXCx3ZIWTbStMCtRGNxv2hxBYKpX3Sgl/aZJPx3ci0TdRmIrCwIlxHlleS4wIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        request.setBizModel(model);
        request.setReturnUrl("http://127.0.0.1:8080/api/common/payback");
        model.setBody("ParkNShop支付");
        model.setSubject(name);
        model.setOutTradeNo(id + "");// outtradeno 生存订单
        model.setTimeoutExpress("60m");
        model.setTotalAmount((price * count) + "");


        Map<String, Object> data = new HashMap<>();
        AlipayTradePrecreateResponse result;
        try {
            result = alipayClient.execute(request);
            data.put("payData", result);
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/api/common/payback")
    public Map<String, String> callback(HttpServletRequest request) {
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JSON.toJSONString(params);
        System.out.println(paramsJson);
        return params;
    }
    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }

}