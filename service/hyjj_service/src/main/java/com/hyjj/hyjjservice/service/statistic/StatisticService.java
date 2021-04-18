package com.hyjj.hyjjservice.service.statistic;

import com.hyjj.hyjjservice.controller.statistic.viewObject.StatisticVo;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    List<StatisticsTargetKey> getStatisticTargetKey(Long parentId);

    Integer addTargetKeyValue(Long reportDataId, List<Double> data);

    List<StatisticVo> getStatisticInfo(List<Integer> years, String areaName, Long targetId, Boolean count);

}
