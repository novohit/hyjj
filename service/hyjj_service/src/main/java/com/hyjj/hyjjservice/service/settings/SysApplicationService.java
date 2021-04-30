package com.hyjj.hyjjservice.service.settings;

import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SysApplicationService {
    List<UserInfoVO> getUserInfoList();
}
