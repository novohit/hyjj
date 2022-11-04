package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.List;

/**
 * 获取累计审核/填报
 */
public class SelectID4 implements GetStatementStrategy{

    private ReportDataMapper reportDataMapper;

    public SelectID4(ReportDataMapper reportDataMapper){
        this.reportDataMapper = reportDataMapper;
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        return reportDataMapper.getStatement(null, null, "审核%通过", userId);
    }

    @Override
    public Integer getStatementSum(String audit, Long userId) {
        return reportDataMapper.getStatementSum(null, null, "审核%通过", userId);
    }
}
