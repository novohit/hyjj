package com.hyjj.hyjjservice.config;

import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.interceptor.UserInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/**");
    }

    @Bean(name = "userThreadLocal")
    public ThreadLocal<User> userThreadLocal(){
        return new ThreadLocal<User>();
    }


}
