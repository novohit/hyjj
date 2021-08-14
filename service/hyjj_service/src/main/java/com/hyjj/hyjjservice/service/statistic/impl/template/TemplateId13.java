package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模板id为13的报表的模板（海水利用情况）
 */
@Component
public class TemplateId13 extends AbstractTargetTemplate {
    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private StatisticService statisticService;

    @Override
    public Integer changeValue(Long reportDataId, List<Double> data) {
        data.remove(1);
        List<StatisticsTargetKey> statisticTargetKey = statisticService.getStatisticTargetKey(140L);
        return addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data, statisticTargetKey);
    }

    @Override
    public Boolean formulaVerification(List<Double> data) {
        return data.get(0) >= data.get(1) &&
                data.get(0) <= data.get(1) * 365 &&
                data.get(3) == data.get(4) + data.get(5) + data.get(6) + data.get(7) + data.get(8);
    }
}
