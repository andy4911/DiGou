package com.digou.controller;

import com.digou.service.ManagerService;
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
public class ManagerController {
    @Resource
    private ManagerService managerService;

    @RequestMapping("/api/m/seller_pass_info")
    public Map<String, Object> sellerPassInfo(HttpServletResponse response) {
        return managerService.sellerPassInfo(response);
    }

    @RequestMapping("/api/m/seller_black_info")
    public Map<String, Object> sellerBlackInfo(HttpServletResponse response) {
        return managerService.sellerBlackInfo(response);
    }

    @RequestMapping("/api/m/seller_black_cancel")
    public Map<String, Object> sellerBlackCancel(HttpServletResponse response,
                                                 @RequestParam(value = "id",required = true) int id) {
        return managerService.sellerBlackCancel(response, id);
    }

    @RequestMapping("/api/m/seller_white_info")
    public Map<String, Object> sellerWhiteInfo(HttpServletResponse response) {
        return managerService.sellerWhiteInfo(response);
    }

    @RequestMapping("/api/m/seller_pass_approve")
    public Map<String, Object> sellerPassApprove(HttpServletResponse response,
                                                 @RequestParam(value = "id",required = true) int id) {
        return managerService.sellerPassApprove(response, id);
    }

    @RequestMapping("/api/m/seller_pass_reject")
    public Map<String, Object> sellerPassReject(HttpServletResponse response,
                                                 @RequestParam(value = "id",required = true) int id) {
        return managerService.sellerPassReject(response, id);
    }

    /*
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
    }*/
}