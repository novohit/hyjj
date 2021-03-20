package com.hyjj.hyjjservice.service.user.impl;


import com.hyjj.hyjjservice.controller.user.viewobject.CaptchaVO;
import com.hyjj.hyjjservice.service.user.CaptchaService;
import com.hyjj.util.tool.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Value("${server.session.timeout:300000}")
    private Long timeout;

    @Autowired
    private RedisUtil redisUtil;


    private final static String CAPTCHA_KEY = "captcha:verification:";

    public CaptchaVO cacheCaptcha(String captcha){
        //生成一个随机标识符
        String captchaKey = UUID.randomUUID().toString();

        //缓存验证码并设置过期时间
        redisUtil.set(CAPTCHA_KEY.concat(captchaKey),captcha,timeout);

        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaKey(captchaKey);
        captchaVO.setExpire(timeout);

        return captchaVO;
    }
}



