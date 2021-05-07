package com.hyjj.hyjjservice.controller.settings;

import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.settings.SysApplicationService;
import com.hyjj.util.responce.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
@RequestMapping("/settings/sys")
@CrossOrigin
public class SysApplicationController {

    @Autowired
    private SysApplicationService sysApplicationService;

    @GetMapping("userList")
    public CommonReturnType getUserList(Integer pageNum,Integer pageSize,UserInfoVO userInfoVO){
        List<UserInfoVO> userInfoList = sysApplicationService.getUserInfoList(userInfoVO, pageNum, pageSize);
        return CommonReturnType.ok().add("userInfoList",userInfoList);
    }

    @GetMapping("userInfo")
    public CommonReturnType getUserInfo(Long id){
        return CommonReturnType.ok().add("info",sysApplicationService.getUserDetail(id));
    }

    @PostMapping("updateUserInfo")
    public CommonReturnType updateUserInfo(@RequestBody User user){
        System.out.println(user);
        return CommonReturnType.ok().add("success",sysApplicationService.updateUserInfo(user));
    }

    @PostMapping("addUserInfo")
    public CommonReturnType addUserInfo(@RequestBody User user){
        return CommonReturnType.ok().add("success",sysApplicationService.insertUserInfo(user));
    }

    @DeleteMapping("deleteUser")
    public CommonReturnType deleteUser(Long id){
        return CommonReturnType.ok().add("success",sysApplicationService.deleteUser(id));
    }

    @GetMapping("notUseComList")
    public CommonReturnType getNotUseComList(){
        return CommonReturnType.ok().add("list",sysApplicationService.getNotUseCom());
    }

    @GetMapping("checkUserName")
    public CommonReturnType checkUserName(String name){
        return CommonReturnType.ok().add("isUsed",sysApplicationService.checkUserName(name));
    }

    @GetMapping("enableUser")
    public CommonReturnType enableUser(Long id){
        return CommonReturnType.ok().add("success",sysApplicationService.enableUser(id));
    }
}
