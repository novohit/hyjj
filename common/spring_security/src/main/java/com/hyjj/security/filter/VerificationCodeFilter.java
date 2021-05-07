package com.hyjj.security.filter;

import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import com.hyjj.util.tool.RedisUtil;
import com.hyjj.util.tool.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerificationCodeFilter extends OncePerRequestFilter {

    @Autowired
    private final RedisUtil redisUtil;

    private final static String  USER_LOGIN = "/user/login";

    public VerificationCodeFilter(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!USER_LOGIN.equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else {
            try {
                verificationCode(request);
                try {
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    ResponseUtil.out(response, CommonReturnType.error(EmBusinessError.USER_LOGIN_FAIL));
                }
            } catch (Exception e) {
                ResponseUtil.out(response, CommonReturnType.error().setErrMsg("验证码不正确"));
            }
        }
    }


    private void verificationCode(HttpServletRequest request) throws Exception {
        String requestCode = request.getParameter("captcha").toUpperCase();
        String captchaId = "captcha:verification:" + request.getParameter("captchaId");

        String truecaptcha = (String) redisUtil.get(captchaId);
        truecaptcha = truecaptcha.toUpperCase();
        if (StringUtils.isEmpty(truecaptcha)) {
            redisUtil.del(captchaId);
        }

        if (StringUtils.isEmpty(requestCode) || StringUtils.isEmpty(truecaptcha) || !requestCode.equals(truecaptcha)) {
            throw new Exception();
        }
    }
}
