package com.hyjj.hyjjservice.service.user.impl;

import com.hyjj.hyjjservice.dao.*;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.hyjjservice.service.common.UidService;
import com.hyjj.hyjjservice.service.user.AuthorityService;
import com.hyjj.hyjjservice.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;



    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private UidService uidService;

    @Override
    public boolean setRoleAuthority(Integer roleId,Integer authorityId) {



        Role role = roleMapper.selectByPrimaryKey(roleId);
        if(role == null || role.getIsUse() == 0){
            return false;
        }

        Authority authority = authorityMapper.selectByPrimaryKey(authorityId);

        if(authority == null || authority.getIsUsed() == 0){
            return false;
        }


        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setRoleId(roleId);
        roleAuthority.setAuthorityId(authorityId);
        roleAuthority.setGmtCreate(new Date());
        roleAuthority.setGmtModified(new Date());
        roleAuthority.setIsUsed(true);
        roleAuthorityMapper.insertSelective(roleAuthority);

        return true;
    }
}
