package com.digou.net;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.digou.net.CookieCheckInterceptor;

@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {
	@Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //需要拦截的
        String[] pathPatterns = {
                "/api/**"
        };

        //不需要拦截的
        String[] excludePathPatterns = {
                "/api/login",
                "/api/register",
        };
        registry.addInterceptor(new CookieCheckInterceptor()).addPathPatterns(pathPatterns).excludePathPatterns(excludePathPatterns);
	}
}
