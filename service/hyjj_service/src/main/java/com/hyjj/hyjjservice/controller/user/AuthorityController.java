package com.hyjj.hyjjservice.controller.user;

import com.hyjj.hyjjservice.service.user.AuthorityService;
import com.hyjj.hyjjservice.service.user.RoleService;
import com.hyjj.hyjjservice.service.user.UserService;
import com.hyjj.util.responce.CommonReturnType;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Role")
public class AuthorityController {


    private RoleService roleService;


    private UserService userService;


    private AuthorityService authorityService;

    public AuthorityController(RoleService roleService, UserService userService, AuthorityService authorityService) {
        this.roleService = roleService;
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @ResponseBody
    @GetMapping("/setUserRole")
    public CommonReturnType setUserRole(Long userId, Integer roleId) {
        boolean jude = userService.setUserRole(userId, roleId);


        return jude ? CommonReturnType.ok() : CommonReturnType.error();
    }

    @ResponseBody
    @GetMapping("/setRoleAuthority")
    public CommonReturnType setRoleAuthority(Integer roleId, Integer authorityId) {
        boolean jude = roleService.setRoleAuthority(roleId, authorityId);


        return jude ? CommonReturnType.ok() : CommonReturnType.error();
    }

    @ResponseBody
    @GetMapping("/setAuthorityPermission")
    public CommonReturnType setAuthorityPermission(Integer authorityId, Integer permissionId) {
        boolean jude = authorityService.setAuthorityPermission(authorityId, permissionId);

        return jude ? CommonReturnType.ok() : CommonReturnType.error();
    }
}
