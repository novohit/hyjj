package com.hyjj.hyjjservice.interceptor;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.dao.UserMapper;

import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.security.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> userThreadLocal;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;


    @Value("${userCache}")
    private String userCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
                return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        GetUser methodAnnotation = method.getAnnotation(GetUser.class);

        if (methodAnnotation != null) {

            String token = request.getHeader("token");
            if (token != null && !"".equals(token.trim())) {
                String userName = tokenManager.getUserFromToken(token);
                User user = (User)redisTemplate.opsForValue().get(userCache + userName);
                if(user == null){
                    user = (User)userMapper.selectByUserName(userName);
                    redisTemplate.opsForValue().set(userCache + userName,user);
                }
                userThreadLocal.set(user);
            }


        }
        return true;
    }
}
