package com.hyjj.hyjjservice.service.settings.impl;

import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.dao.ComInfoMapper;
import com.hyjj.hyjjservice.dao.UserMapper;
import com.hyjj.hyjjservice.dao.UserRoleMapper;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.dataobject.UserRole;
import com.hyjj.hyjjservice.service.settings.SysApplicationService;
import com.hyjj.security.security.DefaultPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class SysApplicationServiceImpl implements SysApplicationService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ComInfoMapper comInfoMapper;

    @Override
    public boolean enableUser(Long id) {
        return userMapper.enableUser(id)!=0;
    }

    @Override
    public List<ComInfo> getNotUseCom() {
        return comInfoMapper.getNotUsedCompanyList();
    }

    @Override
    public List<UserInfoVO> getUserInfoList(UserInfoVO userInfoVO,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize==null?10:pageSize);
        List<UserInfoVO> userInfoVOS = userMapper.selectUserInfoList(userInfoVO);
        return userInfoVOS;
    }

    @Override
    public User getUserDetail(Long id){
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteUser(id)!=0;
    }

    @Override
    public int updateUserInfo(User user) {
        Date date = new Date();
        user.setGmtModified(date);
        if(user.getPassword()!=null){
            if(!user.getPassword().equals(userMapper.selectByPrimaryKey(user.getId()).getPassword())){
                user.setPassword(new DefaultPasswordEncoder().encode(user.getPassword()));
            }
        }
        if(user.getStatue().equals("禁用")){
            userMapper.disableUser(user.getId());
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean checkUserName(String name) {
        return userMapper.checkUserName(name)==1;
    }

    @Override
    public boolean insertUserInfo(User user) {
        Date date = new Date();
        user.setGmtCreate(date);
        user.setGmtModified(date);
        user.setPassword(new DefaultPasswordEncoder().encode(user.getPassword()));
        user.setStatue("正常");
        int i = userMapper.insertSelective(user);
        UserRole userRole = new UserRole();
        userRole.setId(user.getCominfoId());
        userRole.setUserId(user.getId());
        if(user.getUserType().equals("超级管理员")){
            userRole.setRoleId(4);
        }else if(user.getUserType().equals("管理员")){
            userRole.setRoleId(3);
        }else if(user.getUserType().equals("普通用户")){
            userRole.setRoleId(2);
        }
        userRole.setIsUsed(false);
        userRole.setGmtCreate(date);
        userRole.setGmtModified(date);
        int j = userRoleMapper.insertSelective(userRole);

        return i==j;
    }
}
