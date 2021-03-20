package com.hyjj.security.security;

import com.hyjj.security.entity.SecurityUser;
import com.hyjj.util.responce.CommonReturnType;
import com.hyjj.util.tool.R;
import com.hyjj.util.tool.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private AuthenticationManager authenticationManager;
    private TokenManager   tokenManager;
    private RedisTemplate redisTemplate;

    private static final String userAuthority = "authority:";

    public LoginAuthenticationSuccessHandler(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        redisTemplate.opsForValue().set(userAuthority + user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());


        ResponseUtil.out(response, CommonReturnType.ok().add("token", token).add("permission",user.getResourcePathList()).add("role",user.getRole()));
    }
}
