package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfo;
import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfoPo;
import com.hyjj.hyjjservice.controller.fill.viewObject.TableHeadInfo;
import com.hyjj.hyjjservice.dao.param.ComInfoQueryPo;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.service.company.model.CompanyAnalyseModel;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ComInfoMapper {


    List<ComInfo> selectByIndustryEtc(ComInfoQueryPo queryPo);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cominfo
     *
     * @mbg.generated Tue Dec 29 16:21:52 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cominfo
     *
     * @mbg.generated Tue Dec 29 16:21:52 CST 2020
     */
    int insert(ComInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cominfo
     *
     * @mbg.generated Tue Dec 29 16:21:52 CST 2020
     */
    int insertSelective(ComInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cominfo
     *
     * @mbg.generated Tue Dec 29 16:21:52 CST 2020
     */
    ComInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cominfo
     *
     * @mbg.generated Tue Dec 29 16:21:52 CST 2020
     */
    int updateByPrimaryKeySelective(ComInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cominfo
     *
     * @mbg.generated Tue Dec 29 16:21:52 CST 2020
     */
    int updateByPrimaryKey(ComInfo record);

    Long selectCountCompany(ComInfoQueryPo queryPo);

    List<CompanyAnalyseModel> countByRegion();

    List<CompanyAnalyseModel> countByIndustry(@Param("dis") String dis);

    List<CompanyAnalyseModel> countByByComType(@Param("dis") String dis);

    /**
     * 查询所有单位信息
     * @return
     */
    List<ComInfo> selectAllCompany();

    /**
     * 根据用户id查询公司名称
     * @param userId
     * @return
     */
    String selectCompanyNameByUserId(@Param("userId") Long userId);

    List<ComInfo> getCompanyNameList(String name);
    int getCompanyNameListSum(String name);

    List<ComInfo> getNotUsedCompanyList();

    TableHeadInfo getTableHeadInfo(Long reportId);

    List<Long> selectComInfoIds();

    CompanyInfo getCompanyList(@Param("id") Long id);

    List<ComInfo> searchCompanyList(String comName);

    int judgeComCode(String comCode);
}