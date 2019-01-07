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
                                        @RequestParam(value = "password",required = true) String password,
                                        @RequestParam(value = "email",required = true) String email) {
        return sellerService.register(response, url, telephone, shopName, major, description, nickname, password,email);
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
                                        @RequestParam(value = "nickname",required = true) String nickname,
                                        @RequestParam(value = "email",required = true) String email) {
        return sellerService.info_modify(response, id, url, telephone, shopName, major, description, nickname, email);
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
    //待发货、已发货
    @RequestMapping("/api/b/order/send")
    public Map<String, Object> send(HttpServletResponse response,
                                    @RequestParam(value="orderId",required = true) int orderId) {
        return sellerService.send_order(response, orderId);
    }

    //修改商品信息
    @RequestMapping("/api/b/good/modify")
    public Map<String, Object> good_modify(HttpServletResponse response,
                                           @RequestParam(value = "pID",required = true) int pID,
                                           @RequestParam(value = "pName",required = true) String pName,
                                           @RequestParam(value = "num",required = true) int num,
                                           @RequestParam(value = "sID",required = true) int sID,
                                           @RequestParam(value = "description",required = true) String description,
                                           @RequestParam(value = "price",required = true) float price,
                                           @RequestParam(value = "portraitURL",required = true) String portraitURL){
        return sellerService.good_modify(response,pID, pName,  description,  price,  portraitURL,  sID,  num);
    }

    //计算商家日、周、月、年及总收入
    @RequestMapping("/api/b/income/all")
    public Map<String, Object> income(HttpServletResponse response,
                                      @RequestParam(value="id",required = true) int id){
        return sellerService.caculate_income(response,id);
    }

    //下架商品
    @RequestMapping("/api/b/good/delete")
    public Map<String, Object> delete(HttpServletResponse response,
                                      @RequestParam(value = "pId",required = true) int id) {
        return sellerService.good_delete(response, id);
    }

    //选择物流公司
    @RequestMapping("/api/b/logistics/select")
    public Map<String, Object>  logistics(HttpServletResponse response,
                                         @RequestParam(value = "orderId",required = true) int orderId,
                                         @RequestParam(value = "company",required = true) String company,
                                         @RequestParam(value = "point",required = true) String point){
        return sellerService.logistics_select(response,orderId,company,point);
    }

    //申请广告
    @RequestMapping("/api/b/advertises")
    public Map<String, Object>  advertises(HttpServletResponse response,
                                          @RequestParam(value = "sId",required = true) int sId,
                                          @RequestParam(value = "pId",required = true) int pId){
        System.out.println("advertise");
        return sellerService.apply_advertises(response,sId,pId);
    }

    //评论
    @RequestMapping("/api/b/comment")
    public Map<String,Object> comment(HttpServletResponse response,
                                      @RequestParam(value = "pId",required = true) int pId){
        return sellerService.comment(response,pId);
    }

    //查看历史订单
    @RequestMapping("/api/b/order/history")
    public Map<String,Object> order_history(HttpServletResponse response,
                                      @RequestParam(value = "date",required = true) int date,
                                      @RequestParam(value = "sId",required = true) int sId){
        return sellerService.history(response,date,sId);
    }

    //历史订单下search
    @RequestMapping("/api/b/order/history/search")
    public Map<String,Object> order_search(HttpServletResponse response,
                                            @RequestParam(value = "sId",required = true) int sId,
                                            @RequestParam(value = "start",required = true) long start,
                                            @RequestParam(value = "end",required = true) long end){
        return sellerService.search_order(response,sId,start,end);
    }
}