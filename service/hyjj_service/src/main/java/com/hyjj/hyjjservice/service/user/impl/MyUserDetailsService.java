package com.hyjj.hyjjservice.service.user.impl;

import com.hyjj.hyjjservice.dao.*;
import com.hyjj.hyjjservice.dataobject.Authority;
import com.hyjj.hyjjservice.dataobject.Permission;
import com.hyjj.hyjjservice.dataobject.Role;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.security.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Autowired
    private RoleMapper roleMapper;


    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private AuthorityPermissionMapper authorityPermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        com.hyjj.security.entity.User userCur = new com.hyjj.security.entity.User();
        userCur.setUsername(user.getAccount());
        userCur.setPassword(user.getPassword());

        SecurityUser securityUser = new SecurityUser(userCur);

        List<Integer> roleIdList = userRoleMapper.selectRoleIdByUserId(user.getId());
        List<Role> roleList = roleMapper.getRoleList(roleIdList);

        securityUser.setRole(roleList.get(0).getName());
        List<String> authorityRes = new LinkedList<>();
        for(Role r : roleList){
            if(r.getIsUse() == 0){
                continue;
            }
            authorityRes.add(r.getRoleName());
        }

        List<Integer> authorityIdList = roleAuthorityMapper.getAuthorityIdByRoleId(roleIdList);

        List<Authority> authorities = authorityMapper.getAuthorityList(authorityIdList);

        for(Authority a : authorities){
            if(a.getIsUsed() == 0){
                continue;
            }
            authorityRes.add(a.getAuthorityName());
        }

        List<Integer> permissionIdList = authorityPermissionMapper.getPermissionIdByAuthorityId(authorityIdList);

        List<Permission> permissions = permissionMapper.getPermissionList(permissionIdList);



        securityUser.setPermissionValueList(authorityRes);
        List<String> pathList = new LinkedList<>();
        for(Permission p : permissions){
            if(p.getIsUsed() == 0){
                continue;
            }
            pathList.add(p.getPath());
        }
        securityUser.setResourcePathList(pathList);



        return securityUser;

    }
}
