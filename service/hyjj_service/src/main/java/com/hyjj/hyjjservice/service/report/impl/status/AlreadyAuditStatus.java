package com.hyjj.hyjjservice.service.report.impl.status;

import com.hyjj.hyjjservice.dataobject.ReportData;

/**
 * 已审核完的状态
 */
public class AlreadyAuditStatus extends AbstractReportStatus {

    private ReportData reportData;

    public AlreadyAuditStatus(ReportData reportData) {
        this.reportData = reportData;
    }
}
