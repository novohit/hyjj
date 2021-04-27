package com.hyjj.hyjjservice.service.settings.impl;

import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.dao.UserMapper;
import com.hyjj.hyjjservice.service.settings.SysApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysApplicationServiceImpl implements SysApplicationService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserInfoVO> getUserInfoList(){
        List<UserInfoVO> userInfoVOS = userMapper.selectUserInfoList();
        return userInfoVOS;
    }
}
