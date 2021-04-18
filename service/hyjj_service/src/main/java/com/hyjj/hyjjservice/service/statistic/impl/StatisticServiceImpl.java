package com.hyjj.hyjjservice.service.statistic.impl;

import com.hyjj.hyjjservice.controller.statistic.viewObject.StatisticVo;
import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dao.StatisticsTargetKeyMapper;
import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import com.hyjj.hyjjservice.service.statistic.impl.factory.AddTargetStrategyFactory;
import com.hyjj.hyjjservice.service.statistic.impl.template.AddTargetTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private AddTargetStrategyFactory addTargetStrategyFactory;

    @Autowired
    private StatisticsTargetKeyMapper statisticsTargetKeyMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Override
    public List<StatisticsTargetKey> getStatisticTargetKey(Long parentId) {
        return statisticsTargetKeyMapper.selectStatisticsTargetKeyByParentId(parentId);
    }

    @Override
    public Integer addTargetKeyValue(Long reportDataId, List<Double> data) {
        //先根据报表查询出对应模板表
        Long reportTemplateId = reportDataMapper.selectReportTemplateByReportId(reportDataId);
        AddTargetTemplate addTargetValueStatus = addTargetStrategyFactory.getAddTargetValueStatus(reportTemplateId);
        System.out.println(reportTemplateId);
        return addTargetValueStatus.add(reportDataId,data);
    }

    @Override
    public List<StatisticVo> getStatisticInfo(List<Integer> years, String areaName, Long targetId, Boolean count) {
        if (count) {
            List<StatisticsTargetKey> targetKeys = statisticsTargetKeyMapper.selectStatisticsTargetKeyByParentId(targetId);
            List<Long> targetIds = new ArrayList<>();
            List<StatisticVo> result = new ArrayList<>();
            double[] counts = new double[101];    //只统计2000年至2100年的数据，需要时可调整

            for (StatisticsTargetKey targetKey : targetKeys)
                targetIds.add(targetKey.getId());
            /**
             *      2021:1
             *      2021:2
             *      2021:3
             *      2020:4
             *      2020:5
             * -----合并成----->
             *      2021:6
             *      2020:9
             */
            List<StatisticVo> statisticInfoByIds = targetKeyValueMapper.getStatisticInfoByIds(years, areaName, targetIds);
            for (StatisticVo statisticInfoById : statisticInfoByIds)
                counts[statisticInfoById.getYear() - 2000] += statisticInfoById.getTargetValue();

            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    StatisticVo statisticVo = new StatisticVo();
                    statisticVo.setYear(2000 + i);
                    statisticVo.setTargetValue(counts[i]);
                    result.add(statisticVo);
                }
            }
            return result;
        }
        return targetKeyValueMapper.getStatisticInfoById(years, areaName, targetId);
    }
}
