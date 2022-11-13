package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取本周已审核/填报列表
 */
public class SelectID2 implements GetStatementStrategy{
    private final Logger logger = LoggerFactory.getLogger(SelectID2.class);
    private ReportDataMapper reportDataMapper;
    private GregorianCalendar g;

    public SelectID2(ReportDataMapper reportDataMapper){
        this.reportDataMapper = reportDataMapper;
        this.g = new GregorianCalendar();
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        // int i = g.get(Calendar.MONTH)+1;
        // List<ReportData> list = new ArrayList<>();
        logger.info("策略选择 SelectID2");
        return reportDataMapper.getStatementByQuarter("审核%通过", userId)
                .stream()
                .sorted(Comparator.comparing(ReportData::getBeginDate).reversed())
                .collect(Collectors.toList());

    /*    switch (i){
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
        }*/

    }

    @Override
    public Integer getStatementSum(String audit, Long userId) {
        logger.info("策略选择 SelectID2");
        return reportDataMapper.getStatementByQuarterSum("审核%通过", userId);
        /*int i = g.get(Calendar.MONTH) + 1;
        int sum = 0;
        switch (i){
            case 1:
            case 2:
            case 3:
                sum += reportDataMapper.getStatementSum(null, 1, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 2, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 3, "审核%", userId);
                return sum;
            case 4:
            case 5:
            case 6:
                sum += reportDataMapper.getStatementSum(null, 4, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 5, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 6, "审核%", userId);
                return sum;
            case 7:
            case 8:
            case 9:
                sum += reportDataMapper.getStatementSum(null, 7, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 8, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 9, "审核%", userId);
                return sum;
            case 10:
            case 11:
            case 12:
                sum += reportDataMapper.getStatementSum(null, 10, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 11, "审核%", userId);
                sum += reportDataMapper.getStatementSum(null, 12, "审核%", userId);
                return sum;
            default: return 0;
        }*/
    }
}
