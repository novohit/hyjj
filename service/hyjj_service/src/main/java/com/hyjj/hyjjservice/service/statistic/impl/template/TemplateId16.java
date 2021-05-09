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
public class TemplateId16 extends AbstractTargetTemplate {
    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private StatisticService statisticService;

    @Override
    public Integer changeValue(Long reportDataId, List<Double> data) {
        data.remove(1);
        data.remove(2);
        data.remove(3);
        data.remove(4);
        data.remove(5);
        data.remove(6);
        data.remove(7);
        data.remove(8);
        data.remove(9);
        data.remove(10);
        data.remove(11);
        data.remove(12);
        data.remove(13);
        data.remove(14);
        data.remove(15);
        data.remove(16);
        data.remove(17);
        data.remove(18);
        data.remove(19);
        data.remove(20);
        data.remove(21);
        data.remove(22);
        data.remove(23);
        data.remove(24);
        data.remove(25);
        data.remove(26);
        data.remove(27);
        data.remove(28);
        data.remove(29);
        List<Double> data1 = data.subList(0, 3);
        List<Double> data2 = data.subList(23, 27);
        List<Double> data3 = data.subList(27, 29);
        int result = 0;
        List<StatisticsTargetKey> statisticTargetKey1 = statisticService.getStatisticTargetKey(164L);
        result += addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data1, statisticTargetKey1);

        List<StatisticsTargetKey> statisticTargetKey2 = statisticService.getStatisticTargetKey(165L);
        result += addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data2, statisticTargetKey2);

        List<StatisticsTargetKey> statisticTargetKey3 = statisticService.getStatisticTargetKey(166L);
        result += addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data3, statisticTargetKey3);

        return result;
    }
}
