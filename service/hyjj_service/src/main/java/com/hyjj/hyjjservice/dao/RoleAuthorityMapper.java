package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.dataobject.RoleAuthority;

import java.util.List;

public interface RoleAuthorityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    int insert(RoleAuthority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    int insertSelective(RoleAuthority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    RoleAuthority selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    int updateByPrimaryKeySelective(RoleAuthority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    int updateByPrimaryKey(RoleAuthority record);


    List<Integer> getAuthorityIdByRoleId(List<Integer> roleId);


}