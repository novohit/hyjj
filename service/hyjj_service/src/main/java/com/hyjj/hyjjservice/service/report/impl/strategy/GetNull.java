package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.List;

/**
 * 返回null
 */
public class GetNull implements GetStatementStrategy{
    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        return null;
    }

    @Override
    public Integer getStatementSum(String audit, Long userId) {
        return 0;
    }
}
