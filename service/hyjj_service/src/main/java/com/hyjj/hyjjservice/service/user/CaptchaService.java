package com.hyjj.hyjjservice.service.user;


import com.hyjj.hyjjservice.controller.user.viewobject.CaptchaVO;

public interface CaptchaService {
    CaptchaVO cacheCaptcha(String captcha);
}
