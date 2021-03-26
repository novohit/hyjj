package com.hyjj.hyjjservice.service.statistic.impl;

import com.hyjj.hyjjservice.dao.StatisticsTargetKeyMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticsTargetKeyMapper statisticsTargetKeyMapper;

    @Override
    public StatisticsTargetKey getStatisticTargetKey(Long parentId) {
        return statisticsTargetKeyMapper.selectStatisticsTargetKeyByParentId(parentId);
    }
}
