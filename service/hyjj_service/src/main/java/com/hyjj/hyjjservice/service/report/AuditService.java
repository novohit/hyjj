package com.hyjj.hyjjservice.service.report;

import com.hyjj.hyjjservice.controller.report.viewobject.AuditReportVO;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.util.error.BusinessException;

import java.util.List;
import java.util.Map;

public interface AuditService {
    List<Industry> getIndustry();

    List<AuditReportVO> getStatement(AuditVO auditVO, User user, int pageNum, int pageSize) throws BusinessException;

    ReportData getDetailReport(Long reportId);

    String auditReport(Long reportId, Integer judge,String tailHtml, User user);

    List<ComInfo> selectAllCompany();

    List<ReportData> getStatement(Integer select, User user, Boolean isManager, int pageNum, int pageSize);

    String batchAuditReport(Map<Long, Integer> map, User user);

    /**
     * 返回总记录数
     * @param auditVO
     * @param user
     * @return
     */
    Integer getStatementSum(AuditVO auditVO, User user);

    ReportTemplate getAllValueRowAndCol(Integer reportId);

    Integer getStatementSum(Integer select, User user, Boolean isManager);
}
