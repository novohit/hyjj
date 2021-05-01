package com.hyjj.hyjjservice.service.settings;

import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SysApplicationService {
    List<UserInfoVO> getUserInfoList(UserInfoVO userInfoVO,Integer pageNum,Integer pageSize);
    User getUserDetail(Long id);
    int updateUserInfo(User user);
    boolean insertUserInfo(User user);
    boolean deleteUser(Long id);
    List<ComInfo> getNotUseCom();
    boolean checkUserName(String name);
    boolean enableUser(Long id);
}
