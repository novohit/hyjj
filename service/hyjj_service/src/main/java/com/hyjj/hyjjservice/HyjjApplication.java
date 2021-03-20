package com.hyjj.hyjjservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;


@SpringBootApplication
@EnableRedisHttpSession
@ComponentScan("com.hyjj")
@MapperScan(basePackages = {"com.hyjj.hyjjservice.dao","com.xfvape.uid"})
public class HyjjApplication {
    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(HyjjApplication.class, args);
    }
}
