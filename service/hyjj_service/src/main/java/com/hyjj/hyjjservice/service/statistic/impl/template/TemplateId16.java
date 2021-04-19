package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模板id为16的报表的模板（主要海洋产业生产情况）
 */
@Component
public class TemplateId16 extends AddTargetTemplate {
    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private StatisticService statisticService;

    public Integer changeValue(Long reportDataId, List<Double> data) {
        List<Double> data1 = data.subList(0, 2);
        List<Double> data2 = data.subList(23, 26);
        List<Double> data3 = data.subList(27, 28);
        int result = 0;
        List<StatisticsTargetKey> statisticTargetKey1 = statisticService.getStatisticTargetKey(164l);
        result += addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data1, statisticTargetKey1);

        List<StatisticsTargetKey> statisticTargetKey2 = statisticService.getStatisticTargetKey(165l);
        result += addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data2, statisticTargetKey2);

        List<StatisticsTargetKey> statisticTargetKey3 = statisticService.getStatisticTargetKey(165l);
        result += addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data3, statisticTargetKey3);

        return result;
    }
}
