package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.List;

public class SelectID4 implements GetStatementStrategy{

    private ReportDataMapper reportDataMapper;

    public SelectID4(ReportDataMapper reportDataMapper){
        this.reportDataMapper = reportDataMapper;
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        return reportDataMapper.getStatement(null, null, "审核%", userId);
    }
}
