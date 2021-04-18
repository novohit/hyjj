package com.hyjj.hyjjservice.service.report.impl.status;

import com.hyjj.util.responce.CommonReturnType;
import io.swagger.models.auth.In;

public interface ReportStatus {
    /**
     * 提交报表
     *
     * @return
     */
    Integer summitReport();

    /**
     * 审核报表
     *
     * @return
     */
    Integer auditReport();

    /**
     * 审核报表结束
     *
     * @return
     */
    Integer alreadyAuditReport();
}
