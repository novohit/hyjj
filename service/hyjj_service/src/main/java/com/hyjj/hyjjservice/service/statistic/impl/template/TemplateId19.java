package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模板id为19的报表的模板（重点涉海企业情况-工业企业）
 */
@Component
public class TemplateId19 extends AddTargetTemplate {
    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private StatisticService statisticService;

    @Override
    public Integer changeValue(Long reportDataId, List<Double> data) {
        data.remove(0);
        data.remove(0);
        data.remove(0);
        data.remove(0);
        data.remove(0);
        data.remove(0);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(1);
        data.remove(2);
        data.remove(3);
        data.remove(4);
        data.remove(5);
        data.remove(6);
        data.remove(7);
        data.remove(8);
        data.remove(9);
        List<StatisticsTargetKey> statisticTargetKey = statisticService.getStatisticTargetKey(159L);
        return addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data, statisticTargetKey);
    }

    @Override
    public Boolean formulaVerification(List<Long> data) {
        return data.get(2) >= data.get(4);
    }
}
