package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模板id为27的报表的模板（海洋可再生能源利用企业生产情况）
 */
@Component
public class TemplateId27 extends AbstractTargetTemplate {
    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private StatisticService statisticService;

    @Override
    public Integer changeValue(Long reportDataId, List<Double> data) {
        data.remove(2);
        List<StatisticsTargetKey> statisticTargetKey = statisticService.getStatisticTargetKey(150L);
        return addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data, statisticTargetKey);
    }

    @Override
    public Boolean formulaVerification(List<Double> data) {
        return (data.get(0) * 366 * 24) / 10 >= data.get(1) &&
                data.get(2) <= 366 * 24;
    }
}
