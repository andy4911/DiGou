package com.digou.controller;

import com.digou.service.CommonService;
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
public class CommonController {
    @Resource
    private CommonService commonService;

    @RequestMapping("/api/common/search/shop")
    public Map<String, Object> commonSearchShop(HttpServletResponse response,
                                                @RequestParam(value = "value",required = true) String value) {
        return commonService.commonSearchShop(response, value);
    }

}