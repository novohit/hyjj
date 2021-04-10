package com.hyjj.hyjjservice.service.statistic.impl;

import com.hyjj.hyjjservice.controller.statistic.viewObject.StatisticVo;
import com.hyjj.hyjjservice.dao.StatisticsTargetKeyMapper;
import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.dataobject.TargetKeyValue;
import com.hyjj.hyjjservice.dataobject.TargetValue;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticsTargetKeyMapper statisticsTargetKeyMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Override
    public List<StatisticsTargetKey> getStatisticTargetKey(Long parentId) {
        return statisticsTargetKeyMapper.selectStatisticsTargetKeyByParentId(parentId);
    }

    @Override
    public Integer addTargetKeyValue(Long reportDataId, Map<Long, Double> json) {
        Date date = new Date();
        Integer result = 0;

        for (Long targetKeyId : json.keySet()) {
            TargetValue targetValue = new TargetValue();
            targetValue.setTargetValue(json.get(targetKeyId));
            targetValue.setGmtCreate(date);
            targetValue.setGmtModified(date);
            targetValueMapper.insertSelective(targetValue);

            TargetKeyValue targetKeyValue = new TargetKeyValue();
            targetKeyValue.setTargetKeyId(targetKeyId);
            targetKeyValue.setValueId(targetValue.getId());
            targetKeyValue.setGmtCreate(date);
            targetKeyValue.setGmtModified(date);
            targetKeyValue.setReportDataId(reportDataId);
            result += targetKeyValueMapper.insertSelective(targetKeyValue);
        }
        return result;
    }

    @Override
    public List<StatisticVo> getStatisticInfo(List<Integer> years, String areaName, Long targetId, Boolean count) {
        if (count) {
            List<StatisticsTargetKey> targetKeys = statisticsTargetKeyMapper.selectStatisticsTargetKeyByParentId(targetId);
            List<Long> targetIds = new ArrayList<>();

            for (StatisticsTargetKey targetKey : targetKeys)
                targetIds.add(targetKey.getId());

            return targetKeyValueMapper.getStatisticInfoByIds(years, areaName, targetIds);
        }
        return targetKeyValueMapper.getStatisticInfoById(years, areaName, targetId);
    }
}