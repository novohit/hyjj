package com.hyjj.hyjjservice.service.user.impl;

import com.hyjj.hyjjservice.dao.RoleMapper;
import com.hyjj.hyjjservice.dao.UserMapper;
import com.hyjj.hyjjservice.dao.UserRoleMapper;
import com.hyjj.hyjjservice.dataobject.Role;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.dataobject.UserRole;
import com.hyjj.hyjjservice.service.common.UidService;
import com.hyjj.hyjjservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UidService uidService;

    @Override
    public boolean setUserRole(Long userId, Integer roleId) {

        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return false;
        }

        Role role = roleMapper.selectByPrimaryKey(roleId);
        if(role == null || role.getIsUse() == 0){
            return false;
        }


        UserRole userRole = new UserRole();
        userRole.setId(uidService.getUid());
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setGmtCreate(new Date());
        userRole.setGmtModified(new Date());
        userRole.setIsUsed(true);
        userRoleMapper.insertSelective(userRole);

        return true;
    }
}
