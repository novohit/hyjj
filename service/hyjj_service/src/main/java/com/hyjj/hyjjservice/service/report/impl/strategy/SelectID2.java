package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 获取本周已审核/填报列表
 */
public class SelectID2 implements GetStatementStrategy{

    private ReportDataMapper reportDataMapper;
    private GregorianCalendar g;

    public SelectID2(ReportDataMapper reportDataMapper){
        this.reportDataMapper = reportDataMapper;
        this.g = new GregorianCalendar();
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        int i = g.get(Calendar.MONTH);
        List<ReportData> list = new ArrayList<>();
        switch (i){
            case 1:
            case 2:
            case 3:
                list.addAll(reportDataMapper.getStatement(null, 1, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 2, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 3, "审核%", userId));
                return list;
            case 4:
            case 5:
            case 6:
                list.addAll(reportDataMapper.getStatement(null, 4, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 5, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 6, "审核%", userId));
                return list;
            case 7:
            case 8:
            case 9:
                list.addAll(reportDataMapper.getStatement(null, 7, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 8, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 9, "审核%", userId));
                return list;
            case 10:
            case 11:
            case 12:
                list.addAll(reportDataMapper.getStatement(null, 10, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 11, "审核%", userId));
                list.addAll(reportDataMapper.getStatement(null, 12, "审核%", userId));
                return list;
            default: return list;

        }

    }
}
