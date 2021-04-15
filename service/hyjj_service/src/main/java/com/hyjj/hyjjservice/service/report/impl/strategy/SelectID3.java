package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SelectID3 implements GetStatementStrategy{

    private ReportDataMapper reportDataMapper;
    private GregorianCalendar g;

    public SelectID3(ReportDataMapper reportDataMapper){
        this.reportDataMapper = reportDataMapper;
        this.g = new GregorianCalendar();
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        return reportDataMapper.getStatement(null, g.get(Calendar.MONTH) + 1, "审核%", userId);
    }
}
