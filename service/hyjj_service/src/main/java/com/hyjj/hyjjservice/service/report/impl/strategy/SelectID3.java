package com.hyjj.hyjjservice.service.report.impl.strategy;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportData;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取本周已审核/填报列表
 */
public class SelectID3 implements GetStatementStrategy {
    private static final Logger logger = LoggerFactory.getLogger(SelectID3.class);

    private ReportDataMapper reportDataMapper;
    private GregorianCalendar g;

    public SelectID3(ReportDataMapper reportDataMapper) {
        this.reportDataMapper = reportDataMapper;
        this.g = new GregorianCalendar();
    }

    @Override
    public List<ReportData> getStatement(String audit, Long userId) {
        logger.info("查看本月 策略选择 SelectID3");
        return reportDataMapper.getStatementByMonth(g.get(Calendar.MONTH) + 1, "审核%通过", userId)
                .stream()
                .sorted(Comparator.comparing(ReportData::getBeginDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Integer getStatementSum(String audit, Long userId) {
        logger.info("查看本月 策略选择 SelectID3");
        return reportDataMapper.getStatementByMonthSum(g.get(Calendar.MONTH) + 1, "审核%通过", userId);
    }
}
