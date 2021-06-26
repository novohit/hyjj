package com.hyjj.hyjjservice.service.fill;

import com.hyjj.hyjjservice.controller.fill.dto.FormulaVerificationDto;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataHtml;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataList;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportVO;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import com.hyjj.hyjjservice.dataobject.User;

import java.util.List;

public interface FillService {
    List<ReportDataList> getReportListByUserId(Long userid, Integer pageNum, Integer pageSize);

    ReportDataHtml getReportDataHtml(Integer id, Long userId);

    int saveReportDataHtml(ReportDataHtml reportDataHtml);

    int submitReportData(ReportDataHtml reportDataHtml);

    int clearReportData(Integer reportId);

    ReportTemplate getRowAndColByTemplateId(Integer reportId);

    List<String> getReportNumber(List<Long> reportId);

    List<ReportDataList> getStatement(ReportVO reportVO, User user, Integer pageNum, Integer pageSize);

    /**
     * 公式校验
     * @param formulaVerificationDto
     * @return
     */
    String formulaVerification(FormulaVerificationDto formulaVerificationDto);

    Integer getReportListByUserIdSum(Long userId);

    Integer getStatementSum(ReportVO reportVO, User user);
}
