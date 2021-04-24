package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模板id为30的报表的模板（海洋工程建筑项目情况）
 */
@Component
public class TemplateId30 extends AddTargetTemplate {
    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private StatisticService statisticService;

    public Integer changeValue(Long reportDataId, List<Double> data) {
        data.remove(1);
        data.remove(2);
        data.remove(3);
        List<StatisticsTargetKey> statisticTargetKey = statisticService.getStatisticTargetKey(146l);
        return addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data, statisticTargetKey);
    }
}