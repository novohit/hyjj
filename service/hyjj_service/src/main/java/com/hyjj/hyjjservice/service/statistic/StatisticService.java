package com.hyjj.hyjjservice.service.statistic;

import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;

import java.util.List;

public interface StatisticService {
    List<StatisticsTargetKey> getStatisticTargetKey(Long parentId);
}
