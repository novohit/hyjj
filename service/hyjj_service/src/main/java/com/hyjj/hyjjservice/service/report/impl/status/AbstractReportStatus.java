package com.hyjj.hyjjservice.service.report.impl.status;

import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;

/**
 * 默认实现
 */
public abstract class AbstractReportStatus implements ReportStatus {

    public Integer summitReport() { return -1; }

    public Integer auditReport() { return -1; }

    public Integer alreadyAuditReport() {
        return -1;
    }
}
