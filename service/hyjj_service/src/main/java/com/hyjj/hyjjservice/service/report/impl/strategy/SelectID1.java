package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 获取待审核/填报列表
 */
public class SelectID1 implements GetStatementStrategy{

    private final Logger logger = LoggerFactory.getLogger(SelectID1.class);

    private ReportDataMapper reportDataMapper;

    public SelectID1(ReportDataMapper reportDataMapper){
        this.reportDataMapper = reportDataMapper;
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        logger.info("策略选择 SelectID1");
        return reportDataMapper.getStatement(null, null, audit, userId);
    }

    @Override
    public Integer getStatementSum(String audit, Long userId) {
        Integer count = reportDataMapper.getStatementSum(null, null, audit, userId);
        return count;
    }
}
