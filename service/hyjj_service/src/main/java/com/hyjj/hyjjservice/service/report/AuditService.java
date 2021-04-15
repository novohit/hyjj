package com.hyjj.hyjjservice.service.report;

import com.hyjj.hyjjservice.controller.report.viewobject.AuditReportVO;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.util.error.BusinessException;

import java.util.List;

public interface AuditService {
    List<Industry> getIndustry();

    List<AuditReportVO> getStatement(AuditVO auditVO, User user, int pageNum, int pageSize) throws BusinessException;

    ReportData getDetailReport(Long reportId);

    String auditReport(Long reportId, Integer judge, User user);

    List<ComInfo> selectAllCompany();

    List<ReportData> getStatement(Integer select, User user, Boolean isManager, int pageNum, int pageSize);
}
