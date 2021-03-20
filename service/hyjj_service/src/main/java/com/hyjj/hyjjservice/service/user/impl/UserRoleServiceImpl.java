package com.hyjj.hyjjservice.service.user.impl;

import com.hyjj.hyjjservice.dao.UserRoleMapper;
import com.hyjj.hyjjservice.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Integer> selectRoleIdByUserId(Long userId) {
        return userRoleMapper.selectRoleIdByUserId(userId);
    }
}
