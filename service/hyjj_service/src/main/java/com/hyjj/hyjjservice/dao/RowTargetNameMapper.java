package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.dataobject.RowTargetName;

public interface RowTargetNameMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table row_target_name
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table row_target_name
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int insert(RowTargetName record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table row_target_name
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int insertSelective(RowTargetName record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table row_target_name
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    RowTargetName selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table row_target_name
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int updateByPrimaryKeySelective(RowTargetName record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table row_target_name
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int updateByPrimaryKey(RowTargetName record);
}