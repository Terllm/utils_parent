package com.terllm.jwt.config;

import com.terllm.jwt.interceptor.TokenTnterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname TnterceptorConfig
 * @Description 拦截器注册
 * @Date 2020/9/18 16:29
 * @Created by Terllm
 */
@Configuration
public class TnterceptorConfig implements WebMvcConfigurer {


    @Autowired
    TokenTnterceptor tokenTnterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(tokenTnterceptor).addPathPatterns("/**");

    }



}
