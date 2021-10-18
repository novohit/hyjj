package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.List;

public interface GetStatementStrategy {
    List<ReportData> getStatement(String audit, Long userId);
    Integer getStatementSum(String audit, Long userId);
}
