package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取累计审核/填报
 */
public class SelectID4 implements GetStatementStrategy {
    private static final Logger logger = LoggerFactory.getLogger(SelectID4.class);

    private ReportDataMapper reportDataMapper;

    public SelectID4(ReportDataMapper reportDataMapper) {
        this.reportDataMapper = reportDataMapper;
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        logger.info("查看累计审核 策略选择 SelectID4");
        return reportDataMapper.getStatement(null, null, "审核%通过", userId)
                .stream()
                .sorted(Comparator.comparing(ReportData::getBeginDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Integer getStatementSum(String audit, Long userId) {
        logger.info("查看累计审核 策略选择 SelectID4");
        return reportDataMapper.getStatementSum(null, null, "审核%通过", userId);
    }
}
