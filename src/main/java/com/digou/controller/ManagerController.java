package com.digou.controller;

import com.digou.service.ManagerService;
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

    @RequestMapping("/api/m/seller_white_info")
    public Map<String, Object> sellerWhiteInfo(HttpServletResponse response,
                                               @RequestParam(value = "value",required = true) String value) {
        return managerService.sellerWhiteInfo(response, value);
    }

    @RequestMapping("/api/m/seller_black_cancel")
    public Map<String, Object> sellerBlackCancel(HttpServletResponse response,
                                                 @RequestParam(value = "id",required = true) int id) {
        return managerService.sellerBlackCancel(response, id);
    }

    @RequestMapping("/api/m/seller_white_block")
    public Map<String, Object> sellerWhiteBlock(HttpServletResponse response,
                                                 @RequestParam(value = "id",required = true) int id) {
        return managerService.sellerWhiteBlock(response, id);
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

    @RequestMapping("/api/m/customer_black_info")
    public Map<String, Object> customerBlackInfo(HttpServletResponse response) {
        return managerService.customerBlackInfo(response);
    }
    @RequestMapping("/api/m/search/customer")
    public Map<String, Object> managerSearchCustomer(HttpServletResponse response,
                                                @RequestParam(value = "value",required = true) String value) {
        return managerService.managerSearchCustomer(response, value);
    }
    @RequestMapping("/api/m/customer_black_cancel")
    public Map<String, Object> customerBlackCancel(HttpServletResponse response,
                                                 @RequestParam(value = "id",required = true) int id) {
        return managerService.customerBlackCancel(response, id);
    }

    @RequestMapping("/api/m/customer_white_block")
    public Map<String, Object> customerWhiteBlock(HttpServletResponse response,
                                                @RequestParam(value = "id",required = true) int id) {
        return managerService.customerWhiteBlock(response, id);
    }
    @RequestMapping("/api/m/all_order")
    public Map<String,Object> allOrder(HttpServletResponse response){
        return managerService.allOrder(response);
    }
    @RequestMapping("/api/m/day_order")
    public Map<String,Object> dayOrder(HttpServletResponse response,
                                       @RequestParam(value  = "year",required = true) int year ,
                                       @RequestParam(value  = "month",required = true) int month ,
                                       @RequestParam(value  = "day",required = true) int day ){
        return managerService.dayOrder(response,year, month,day);

    }
    @RequestMapping("/api/m/month_order")
    public Map<String,Object> monthOrder(HttpServletResponse response,
                                       @RequestParam(value  = "year",required = true) int year ,
                                       @RequestParam(value  = "month",required = true) int month ){
        return managerService.monthOrder( response,year, month);

    }
    @RequestMapping("/api/m/year_order")
    public Map<String,Object> yearOrder(HttpServletResponse response,
                                       @RequestParam(value  = "year",required = true) int year ){
        return managerService.yearOrder( response,year);

    }
    @RequestMapping("/api/m/week_order")
    public Map<String,Object> weekOrder(HttpServletResponse response,
                                        @RequestParam(value  = "year",required = true) int year,
                                        @RequestParam(value  = "month",required = true) int month ,
                                        @RequestParam(value  = "day",required = true) int day ,
                                        @RequestParam(value = "days", required = true)int days){
        return managerService.weekOrder( response,year,month,day ,days);

    }
    @RequestMapping("/api/m/change_profit_rate")
    public Map<String,Object> changeProfitRate(HttpServletResponse response,
                                        @RequestParam(value  = "rate",required = true) float rate ){
        return managerService.changeProfitRate( response,rate);

    }
    @RequestMapping("/api/m/search_order")
    public Map<String,Object> searchOrder(HttpServletResponse response,
                                               @RequestParam(value  = "orderID",required = true) int  orderID ){
        System.out.println("ffff");
        return managerService.searchOderByID( response,orderID);

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