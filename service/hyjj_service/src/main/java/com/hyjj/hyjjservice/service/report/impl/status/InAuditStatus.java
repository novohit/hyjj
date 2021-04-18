package com.hyjj.hyjjservice.service.report.impl.status;

import com.hyjj.hyjjservice.dataobject.ReportData;
import com.hyjj.util.responce.CommonReturnType;

/**
 * 审核中状态
 */
public class InAuditStatus extends AbstractReportStatus {

    private ReportData reportData;

    public InAuditStatus(ReportData reportData) {
        this.reportData = reportData;
    }

    @Override
    public Integer auditReport() {
        reportData.setReportStatus(reportData.getAlreadyAuditStatue());
        return 1;
    }
}
