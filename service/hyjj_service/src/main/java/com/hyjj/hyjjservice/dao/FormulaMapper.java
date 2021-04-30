package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.controller.settings.viewObject.FormulaListVO;
import com.hyjj.hyjjservice.dataobject.Formula;

import java.util.List;

public interface FormulaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table formula
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table formula
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int insert(Formula record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table formula
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int insertSelective(Formula record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table formula
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    Formula selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table formula
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int updateByPrimaryKeySelective(Formula record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table formula
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    int updateByPrimaryKey(Formula record);

    List<FormulaListVO> getFormulaList();

    List<FormulaListVO> getFormulaByFormName(String formName);


}