package com.hyjj.hyjjservice.service.report;

import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.util.error.BusinessException;

import java.util.List;

public interface AuditService {
    public List<Industry> getIndustry();

    public List<ReportData> getStatement(AuditVO auditVO, User user) throws BusinessException;

    public ReportData getDetailReport(Long reportId);

    String auditReport(Long reportId, Integer judge, User user);

    List<ComInfo> selectAllCompany();

    String urge(User user, String year, String company);

    UrgeData getUrge(String year, String company);

    public List<ReportData> getStatement(Integer select, User user);
}
