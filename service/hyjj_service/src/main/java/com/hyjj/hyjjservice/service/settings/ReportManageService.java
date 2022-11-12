package com.hyjj.hyjjservice.service.settings;

import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateInfoVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.UnitFillReportVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.FormulaListVO;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.Formula;
import com.hyjj.hyjjservice.dataobject.Gdp;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;

public interface ReportManageService {
    List<FormulaListVO> getFormulaList(Integer pageNum,Integer pageSize);
    List<FormulaListVO> getFormulaByFormName(String formName,Integer pageNum,Integer pageSize);
    List<ReportTemplateVO> getReportTemplateList(ReportTemplateVO reportTemplateVO);
    List<ComInfo> getComInfoList(String name,Integer pageNum, Integer pageSize);
    List<UnitFillReportVO> getNeededFillList(Long id);
    List<UnitFillReportVO> getNotNeededFillList(Long id);
    Boolean setFillReportList(Long comInfoId,String reportIds);
    ReportTemplateInfoVO getReportTemplateInfo(Integer id);
    List<Gdp> getCurrentYearData(Integer pageNum,Integer pageSize);
    List<Gdp> getPassYearData(Integer pageNum,Integer pageSize);
    int updateGdpData(Gdp gdpObj);
    List<Gdp> searchGdpData(String district,String year,Integer pageNum, Integer pageSize);
    Gdp getGdpDataById(Integer id);
    int deleteGdpDataById(Integer id);
    int insertGdpDataById(Gdp gdpObj);
    boolean manualCreateReport(String endDate,Long id) throws Exception;
    boolean manualCreateReportV2(String endDate, String beginDate, Long id) throws Exception;
    int getFormulaListSum();
    int getSearchFormulaSum(String formName);
    int getCurrentYearDataSum();
    int getPassYearDataSum();
    int getSearchGpaDataSum(String district,String year);
    int getReportTemplateListSum(ReportTemplateVO reportTemplateVO);
    int getComInfoListSum(String name);
    boolean oneKeyCreateReport(String endDate) throws Exception;
    boolean oneKeyCreateReportV2(String endDate, String beginDate) throws Exception;
}
