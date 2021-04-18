package com.hyjj.hyjjservice.service.report.impl.status;

import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.List;

public class StatusUtil {
    public static void setStatus(List<ReportData> reportDataList) {
        for (ReportData reportData : reportDataList) {
            setStatus(reportData);
        }
    }

    public static void setStatus(ReportData reportData) {
        String proStatusName = reportData.getProStatusName();
        if (proStatusName.equals("填报数据")) {
            reportData.setReportStatus(reportData.getFillReportStatue());
        } else if (proStatusName.equals("审核数据")) {
            reportData.setReportStatus(reportData.getInAuditStatue());
        } else if (proStatusName.equals("审核通过") || proStatusName.equals("审核不通过")) {
            reportData.setReportStatus(reportData.getAlreadyAuditStatue());
        }

    }
}
