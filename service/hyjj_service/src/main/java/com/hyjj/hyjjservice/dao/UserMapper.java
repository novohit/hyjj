package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.dataobject.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Mar 06 22:10:39 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Mar 06 22:10:39 CST 2021
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Mar 06 22:10:39 CST 2021
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Mar 06 22:10:39 CST 2021
     */
    User selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Mar 06 22:10:39 CST 2021
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Mar 06 22:10:39 CST 2021
     */
    int updateByPrimaryKey(User record);

    User selectByUserName(@Param("username") String userName);

    List<UserInfoVO> selectUserInfoList(@Param("obj") UserInfoVO userInfoVO);
    User selectByComInfoId(Long comId);
    int deleteUser(Long id);
    int checkUserName(String name);
    int enableUser(Long id);
    int disableUser(Long id);
    int selectUserInfoListSum(UserInfoVO userInfoVO);

}