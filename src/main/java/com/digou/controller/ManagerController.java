package com.digou.controller;

import com.digou.service.ManagerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping("/api/m/search_order_by_time")
    public Map<String,Object> searchOrderByTime(HttpServletResponse response,
                                       @RequestParam(value  = "start",required = true) long start ,
                                       @RequestParam(value  = "end",required = true) long end ){
        return managerService.searchOrderByTime(response,start,end);
    }
    @RequestMapping("/api/m/change_profit_rate")
    public Map<String,Object> changeProfitRate(HttpServletResponse response,
                                        @RequestParam(value  = "rate",required = true) float rate ){
        return managerService.changeProfitRate( response,rate);

    }
    @RequestMapping("/api/m/search_order")
    public Map<String,Object> searchOrder(HttpServletResponse response,
                                               @RequestParam(value  = "orderID",required = true) int  orderID ){
        return managerService.searchOrderByID( response,orderID);

    }
    @RequestMapping("/api/m/profit_rate")
    public Map<String,Object> profitRate(HttpServletResponse response){
        return managerService.profitRate( response);

    }

    @RequestMapping("/api/m/all_seller_info")
    public Map<String, Object> allSellerInfo(HttpServletResponse response) {
        return managerService.allSellerInfo(response);
    }

    @RequestMapping("/api/m/seller_top5_info")
    public Map<String, Object> sellerTop5Info(HttpServletResponse response) {
        return managerService.sellerTop5Info(response);
    }
    @RequestMapping("/api/m/seller_top5_info_ads")
    public Map<String, Object> sellerTop5InfoAds(HttpServletResponse response) {
        return managerService.sellerTop5InfoAds(response);
    }

    @RequestMapping("/api/m/seller_to_top5")
    public Map<String, Object> sellerToTop5(HttpServletResponse response,
                                            @RequestParam(value = "id",required = true) int id) {
        return managerService.sellerToTop5(response, id);
    }

    @RequestMapping("/api/m/seller_top5_cancel")
    public Map<String, Object> sellerTop5Cancel(HttpServletResponse response,
                                                @RequestParam(value = "id",required = true) int id) {
        return managerService.sellerTop5Cancel(response, id);
    }
    @RequestMapping("/api/m/product_top10_info")
    public Map<String, Object> productTop10Info(HttpServletResponse response) {
        return managerService.productTop10Info(response);
    }

    @RequestMapping("/api/m/product_to_top10")
    public Map<String, Object> productToTop10(HttpServletResponse response,
                                            @RequestParam(value = "id",required = true) int id) {
        return managerService.productToTop10(response, id);
    }

    @RequestMapping("/api/m/product_top10_cancel")
    public Map<String, Object> productTop10Cancel(HttpServletResponse response,
                                                @RequestParam(value = "id",required = true) int id) {
        return managerService.productTop10Cancel(response, id);
    }
    @RequestMapping("/api/m/top5_seller_apply_info")
    public Map<String, Object> top5SellerApplyInfo(HttpServletResponse response) {
        return managerService.top5SellerApplyInfo(response);
    }
    @RequestMapping("/api/m/top5_seller_apply_reject")
    public Map<String, Object> top5SellerApplyReject(HttpServletResponse response,
                                                     @RequestParam(value = "id",required = true) int id) {
        return managerService.top5SellerApplyReject(response,id);
    }
    @RequestMapping("/api/m/top10_product_apply_info")
    public Map<String, Object> top10ProductApplyInfo(HttpServletResponse response) {
        return managerService.top10ProductApplyInfo(response);
    }
    @RequestMapping("/api/m/top10_product_apply_reject")
    public Map<String, Object> top10ProductApplyReject(HttpServletResponse response,
                                                     @RequestParam(value = "id",required = true) int id) {
        return managerService.top10ProductApplyReject(response,id);
    }
    //method= RequestMethod.GET
    @RequestMapping("/api/m/mysql_backup")
    public void mysqlBackup(HttpServletResponse response) {
        managerService.Download(response);
    }

    @RequestMapping("/api/m/income/all")
    public Map<String, Object> caculate_income(HttpServletResponse response){
        return managerService.caculate_income(response);
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