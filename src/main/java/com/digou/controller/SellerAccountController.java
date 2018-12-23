package com.digou.controller;

import com.digou.common.ResponseCommon;
import com.digou.service.AccountService;
import com.digou.service.SellerAccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@ComponentScan({"com.digou.service"})
@MapperScan("com.digou.mapper")
public class SellerAccountController {
    @Resource
    private SellerAccountService sellerAccountService;

    @RequestMapping("/api/b/login")
    public Map<String, Object> login(HttpServletResponse response,
                                        @RequestParam(value = "telephone",required = true) String telephone,
                                        @RequestParam(value = "password",required = true) String password) {
        return sellerAccountService.login(response, telephone, password);
    }

    @RequestMapping("/api/b/register")
    public Map<String, Object> register(HttpServletResponse response,
                                        @RequestParam(value = "url",required = true) String url,
                                        @RequestParam(value = "telephone",required = true) String telephone,
                                        @RequestParam(value = "shopName",required = true) String shopName,
                                        @RequestParam(value = "major",required = true) String major,
                                        @RequestParam(value = "description",required = true) String description,
                                        @RequestParam(value = "nickname",required = true) String nickname,
                                        @RequestParam(value = "password",required = true) String password) {
        return sellerAccountService.register(response, url, telephone, shopName, major, description, nickname, password);
    }

    @RequestMapping("/api/b/info/get")
    public Map<String, Object> info_get(HttpServletResponse response,
                                        @RequestParam(value = "id",required = true) int id) {
        return sellerAccountService.info_get(response, id);
    }

    @RequestMapping("/api/b/info/modify")
    public Map<String, Object> info_modify(HttpServletResponse response,
                                        @RequestParam(value = "id",required = true) int id,
                                        @RequestParam(value = "url",required = true) String url,
                                        @RequestParam(value = "telephone",required = true) String telephone,
                                        @RequestParam(value = "shopName",required = true) String shopName,
                                        @RequestParam(value = "major",required = true) String major,
                                        @RequestParam(value = "description",required = true) String description,
                                        @RequestParam(value = "nickname",required = true) String nickname) {
        return sellerAccountService.info_modify(response, id, url, telephone, shopName, major, description, nickname);
    }
}