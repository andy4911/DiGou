package com.digou.controller;

import com.digou.service.SellerService;
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
public class SellerController {
    @Resource
    private SellerService sellerService;

    @RequestMapping("/api/b/login")
    public Map<String, Object> login(HttpServletResponse response,
                                        @RequestParam(value = "telephone",required = true) String telephone,
                                        @RequestParam(value = "password",required = true) String password) {
        return sellerService.login(response, telephone, password);
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
        return sellerService.register(response, url, telephone, shopName, major, description, nickname, password);
    }

    @RequestMapping("/api/b/info/get")
    public Map<String, Object> info_get(HttpServletResponse response,
                                        @RequestParam(value = "id",required = true) int id) {
        return sellerService.info_get(response, id);
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
        return sellerService.info_modify(response, id, url, telephone, shopName, major, description, nickname);
    }

    @RequestMapping("/api/b/good/add")
    public Map<String, Object> good_add(HttpServletResponse response,
                                        @RequestParam(value = "id",required = true) int id,
                                        @RequestParam(value = "url",required = true) String url,
                                        @RequestParam(value = "goodName",required = true) String goodName,
                                        @RequestParam(value = "price",required = true) float price,
                                        @RequestParam(value = "description",required = true) String description,
                                        @RequestParam(value = "num",required = true) int num) {
        return sellerService.good_add(response, id, url, goodName, price, description, num);
    }

    @RequestMapping("/api/b/good/all")
    public Map<String, Object> good_all(HttpServletResponse response,
                                        @RequestParam(value = "id",required = true) int id) {
        return sellerService.good_all(response, id);
    }

    //以下是郭伟明的代码
    //商家查看订单
    @RequestMapping("/api/b/order/all")
    public Map<String, Object> look_order(HttpServletResponse response,
                                          @RequestParam(value="id",required = true) int id){
        return sellerService.look_order(response,id );
    }

    //商家退款
    @RequestMapping("/api/b/order/refund")
    public Map<String, Object> refund_order(HttpServletResponse response,
                                          @RequestParam(value="orderId",required = true) int orderId) {
        return sellerService.refund_order(response, orderId);
    }

    //修改商品信息
    @RequestMapping("/api/b/good/modify")
    public Map<String, Object> good_modify(HttpServletResponse response,
                                           @RequestParam(value = "pID",required = true) int pID,
                                           @RequestParam(value = "pName",required = true) String pName,
                                           @RequestParam(value = "num",required = true) int num,
                                           @RequestParam(value = "num",required = true) int sID,
                                           @RequestParam(value = "description",required = true) String description,
                                           @RequestParam(value = "price",required = true) float price,
                                           @RequestParam(value = "portraitURL",required = true) String portraitURL
                                            ){
        return sellerService.good_modify(response,pID, pName,  description,  price,  portraitURL,  sID,  num);
    }
}