package com.hyjj.hyjjservice.config;

import com.hyjj.security.filter.VerificationCodeFilter;
import com.hyjj.util.tool.RedisUtil;
import com.hyjj.util.validator.ValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;

@Configuration
public class CommonConfig {
    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }

    @Bean
    public VerificationCodeFilter verificationCodeFilter(RedisUtil redisUtil){
        return new VerificationCodeFilter(redisUtil);
    }

    @Bean
    public ValidatorImpl validatorImpl(){
        return new ValidatorImpl();
    }
}
