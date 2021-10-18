package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.List;

/**
 * 获取待审核/填报列表
 */
public class SelectID1 implements GetStatementStrategy{

    private ReportDataMapper reportDataMapper;

    public SelectID1(ReportDataMapper reportDataMapper){
        this.reportDataMapper = reportDataMapper;
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        return reportDataMapper.getStatement(null, null, audit, userId);
    }

    @Override
    public Integer getStatementSum(String audit, Long userId) {
        Integer count = reportDataMapper.getStatementSum(null, null, audit, userId);
        return count;
    }
}
