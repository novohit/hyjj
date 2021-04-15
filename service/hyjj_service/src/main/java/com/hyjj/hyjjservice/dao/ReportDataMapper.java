package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.controller.report.viewobject.AuditReportVO;
import com.hyjj.hyjjservice.controller.report.viewobject.UrgeReportVO;
import com.hyjj.hyjjservice.dataobject.ReportData;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataHtml;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_data
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_data
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int insert(ReportData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_data
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int insertSelective(ReportData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_data
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    ReportData selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_data
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int updateByPrimaryKeySelective(ReportData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report_data
     *
     * @mbg.generated Sat Jan 09 22:49:25 CST 2021
     */
    int updateByPrimaryKey(ReportData record);

    /**
     * 查询报表
     *
     * @param industriesIds
     * @return
     */
    List<AuditReportVO> selectReportDataByIndustryId(@Param("industriesIds") List<Integer> industriesIds,
                                                     @Param("type") Integer type,
                                                     @Param("statue") String statue,
                                                     @Param("year") String year,
                                                     @Param("nextYear") String nextYear);

    /**
     * 行业全选的情况
     * @return
     */
    List<AuditReportVO> selectAllIndustryReportData(@Param("type") Integer type,
                                                 @Param("statue") String statue,
                                                 @Param("year") String year,
                                                 @Param("nextYear") String nextYear);

    /**
     * 审核指定id的报表
     */
    void updateProcessByReportId(@Param("reportId") Long reportId,
                                 @Param("processId") Long processId,
                                 @Param("proStatus") String proStatus,
                                 @Param("proStatusName") String proStatusName);


    List<ReportDataList> getReportDataListByUserId(Long userId);

    ReportDataHtml getReportDataHtml(int id,Long userId);

    int saveReportDataHtml(ReportDataHtml reportDataHtml);

    int submitReportDataHtml(ReportDataHtml reportDataHtml);

    ReportTemplate getRowAndColByTemplateId(Integer reportId);


    int clearReportDataByReportId(Integer reportId);

    /**
     * 首页那几个功能
     * @param week
     * @param month
     * @param audit
     * @return
     */
    List<ReportData> getStatement(@Param("week") Integer week,
                                  @Param("month") Integer month,
                                  @Param("audit") String audit,
                                  @Param("userId") Long userId);

    List<UrgeReportVO> selectByYearAndCompany(@Param("year") Integer years,
                                              @Param("company") String company);
    List<ReportDataList> reportSelectAllIndustryReportData(@Param("type") Integer type,
                                                    @Param("statue") String statue,
                                                    @Param("year") String year,
                                                    @Param("nextYear") String nextYear,
                                                    @Param("userId")Long userId);

    List<ReportDataList> reportSelectReportDataByIndustryId(@Param("industriesIds") List<Integer> industriesIds,
                                                     @Param("type") Integer type,
                                                     @Param("statue") String statue,
                                                     @Param("year") String year,
                                                     @Param("nextYear") String nextYear,
                                                            @Param("userId")Long userId);
}