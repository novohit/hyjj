package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模板id为11的报表的模板（海洋化工企业生产情况）
 */
@Component
public class TemplateId12 extends AbstractTargetTemplate {

    @Override
    public Integer changeValue(Long reportDataId, List<Double> data) {
        return 1;
    }

    @Override
    public Boolean formulaVerification(List<Double> data) {
        return data.get(0) >= data.get(1);
    }
}
