package com.huangcheng.community.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 荒城
 * @title: WebConfig
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/107:56
 */
@Configuration
//@EnableWebMvc

public class WebConfig implements WebMvcConfigurer {



    @Autowired
    private SessionInterceptor sesstionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(sesstionInterceptor).addPathPatterns("/**");

    }


}
