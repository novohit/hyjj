package com.hyjj.hyjjservice.service.user.impl;

import com.hyjj.hyjjservice.dao.AuthorityMapper;
import com.hyjj.hyjjservice.dao.AuthorityPermissionMapper;
import com.hyjj.hyjjservice.dao.PermissionMapper;
import com.hyjj.hyjjservice.dataobject.Authority;
import com.hyjj.hyjjservice.dataobject.AuthorityPermission;
import com.hyjj.hyjjservice.dataobject.Permission;
import com.hyjj.hyjjservice.service.user.AuthorityService;
import com.hyjj.hyjjservice.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class AuthorityServiceImpl implements AuthorityService {


    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private AuthorityPermissionMapper authorityPermissionMapper;



    @Override
    public boolean setAuthorityPermission(Integer authorityId, Integer permissionId) {

        Authority authority = authorityMapper.selectByPrimaryKey(authorityId);
        if(authority == null || authority.getIsUsed() == 0){
            return false;
        }


        Permission permission = permissionMapper.selectByPrimaryKey(permissionId);
        if(permission == null || permission.getIsUsed() == 0){
            return false;
        }

        AuthorityPermission authorityPermission = new AuthorityPermission();
        authorityPermission.setAuthorityId(authorityId);
        authorityPermission.setPermissionId(permissionId);
        authorityPermission.setGmtCreate(new Date());
        authorityPermission.setGmtModified(new Date());
        authorityPermission.setIsUsed(true);

        authorityPermissionMapper.insert(authorityPermission);
        return true;
    }
}
