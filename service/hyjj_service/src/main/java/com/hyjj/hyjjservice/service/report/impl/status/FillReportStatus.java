package com.hyjj.hyjjservice.service.report.impl.status;

import com.hyjj.hyjjservice.dataobject.ReportData;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.models.auth.In;

/**
 * 填报中状态
 */
public class FillReportStatus extends AbstractReportStatus {

    private ReportData reportData;

    public FillReportStatus(ReportData reportData) {
        this.reportData = reportData;
    }

    @Override
    public Integer summitReport() {
        reportData.setReportStatus(reportData.getInAuditStatue());
        return 1;
    }
}
