package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateInfoVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.SearchReportTemplateVO;
import com.hyjj.hyjjservice.dataobject.ReportData;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_template
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_template
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int insert(ReportTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_template
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int insertSelective(ReportTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_template
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    ReportTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_template
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int updateByPrimaryKeySelective(ReportTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_template
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int updateByPrimaryKey(ReportTemplate record);

    List<String> getReportNumber(@Param("reportTemplateIds") List<Long> reportTemplateIds);

    List<ReportTemplateVO> getReportTemplateList(@Param("reportTemplateVO") ReportTemplateVO reportTemplateVO);

    ReportTemplateInfoVO getReportTemplateInfo(Integer id);

}