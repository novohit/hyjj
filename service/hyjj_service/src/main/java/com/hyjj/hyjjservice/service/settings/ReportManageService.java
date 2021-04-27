package com.hyjj.hyjjservice.service.settings;

import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateInfoVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.UnitFillReportVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.FormulaListVO;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.Formula;

import java.util.List;

public interface ReportManageService {
    List<FormulaListVO> getFormulaList(Integer id,Integer pageNum,Integer pageSize);
    List<FormulaListVO> getFormulaByFormName(String formName,Integer pageNum,Integer pageSize);
    List<ReportTemplateVO> getReportTemplateList(ReportTemplateVO reportTemplateVO);
    List<ComInfo> getComInfoList(Integer pageNum, Integer pageSize);
    List<UnitFillReportVO> getNeededFillList(Long id);
    List<UnitFillReportVO> getNotNeededFillList(Long id);
    Boolean setFillReportList(Long comInfoId,String reportIds);
    ReportTemplateInfoVO getReportTemplateInfo(Integer id);
}
