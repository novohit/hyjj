package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.dataobject.Industry;
import io.swagger.models.auth.In;

import java.util.List;

public interface IndustryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table industry
     *
     * @mbg.generated Tue Dec 29 10:48:10 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table industry
     *
     * @mbg.generated Tue Dec 29 10:48:10 CST 2020
     */
    int insert(Industry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table industry
     *
     * @mbg.generated Tue Dec 29 10:48:10 CST 2020
     */
    int insertSelective(Industry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table industry
     *
     * @mbg.generated Tue Dec 29 10:48:10 CST 2020
     */
    Industry selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table industry
     *
     * @mbg.generated Tue Dec 29 10:48:10 CST 2020
     */
    int updateByPrimaryKeySelective(Industry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table industry
     *
     * @mbg.generated Tue Dec 29 10:48:10 CST 2020
     */
    int updateByPrimaryKey(Industry record);

    List<Industry> selectAllIndustry();

    List<Industry> selectSomeIndustry();
}