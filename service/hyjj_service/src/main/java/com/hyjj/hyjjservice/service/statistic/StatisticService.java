package com.hyjj.hyjjservice.service.statistic;

import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;

public interface StatisticService {
    StatisticsTargetKey getStatisticTargetKey(Long parentId);
}
