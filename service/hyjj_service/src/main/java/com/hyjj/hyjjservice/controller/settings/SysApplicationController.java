package com.hyjj.hyjjservice.controller.settings;

import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.service.settings.SysApplicationService;
import com.hyjj.util.responce.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
@RequestMapping("/settings/sys")
public class SysApplicationController {

    @Autowired
    private SysApplicationService sysApplicationService;

    @GetMapping("userList")
    public CommonReturnType getUserList(){
        List<UserInfoVO> userInfoList = sysApplicationService.getUserInfoList();
        System.out.println(userInfoList);
        return CommonReturnType.ok().add("userInfoList",userInfoList);
    }
}
