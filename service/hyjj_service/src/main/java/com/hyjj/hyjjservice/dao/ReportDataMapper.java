package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.controller.report.viewobject.AuditReportVO;
import com.hyjj.hyjjservice.controller.urge.viewobject.UrgeReportVO;
import com.hyjj.hyjjservice.dataobject.ReportData;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataHtml;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
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
     *
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
                                 @Param("proStatusName") String proStatusName,
                                 @Param("tailHtml") String tailHtml,
                                 @Param("isSave") Integer isSave);


  //  @Select("select id,number,title,end_date,fill_unit,begin_date,pro_status from report_data where user_id = #{userId} order by end_date desc")
    List<ReportDataList> getReportDataListByUserId(Long userId);

    ReportDataHtml getReportDataHtml(int id, Long userId);

    int saveReportDataHtml(ReportDataHtml reportDataHtml);

    int submitReportData(ReportDataHtml reportDataHtml);

    ReportTemplate getRowAndColByReportId(Integer reportId);


    int clearReportDataByReportId(Integer reportId);

    /**
     * 首页那几个功能
     *
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
                                                           @Param("userId") Long userId);

    List<ReportDataList> reportSelectReportDataByIndustryId(@Param("industriesIds") List<Integer> industriesIds,
                                                            @Param("type") Integer type,
                                                            @Param("statue") String statue,
                                                            @Param("year") String year,
                                                            @Param("nextYear") String nextYear,
                                                            @Param("userId") Long userId);

    /**
     * 根据报表查询模板表id
     *
     * @param reportId
     * @return
     */
    Long selectReportTemplateByReportId(@Param("reportId") Long reportId);

    /**
     * 返回催办名单总条数
     *
     * @param year
     * @param company
     * @return
     */
    Integer selectByYearAndCompanySum(Integer year, String company);

    Integer getReportDataListByUserIdSum(Long userId);

    Integer reportSelectAllIndustryReportDataSum(Integer type, String status, String year, String nextYear, Long userId);

    Integer reportSelectReportDataByIndustryIdSum(List<Integer> industriesIds, Integer type, String status, String year, String nextYear, Long userId);


    /**
     * 行业全选，返回总条数
     *
     * @param type
     * @param statue
     * @param year
     * @param nextYear
     * @return
     */
    Integer selectAllIndustryReportDataSum(@Param("type") Integer type,
                                           @Param("statue") String statue,
                                           @Param("year") String year,
                                           @Param("nextYear") String nextYear);

    Integer selectReportDataByIndustryIdSum(@Param("industriesIds") List<Integer> industriesIds,
                                            @Param("type") Integer type,
                                            @Param("statue") String statue,
                                            @Param("year") String year,
                                            @Param("nextYear") String nextYear);
    Integer judgeIfExists(Long id);

}