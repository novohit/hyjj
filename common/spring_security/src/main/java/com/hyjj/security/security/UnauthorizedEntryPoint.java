package com.hyjj.security.security;


import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import com.hyjj.util.tool.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException){

        ResponseUtil.out(response, CommonReturnType.error(EmBusinessError.USER_DONOT_HVER_PERMISSION));
    }
}
