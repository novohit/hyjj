package com.hyjj.hyjjservice.service.statistic.impl;

import com.hyjj.hyjjservice.controller.statistic.viewObject.StatisticVo;
import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dao.StatisticsTargetKeyMapper;
import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import com.hyjj.hyjjservice.service.statistic.impl.factory.AddTargetStrategyFactory;
import com.hyjj.hyjjservice.service.statistic.impl.template.AbstractTargetTemplate;
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
        AbstractTargetTemplate addTargetValueStatus = addTargetStrategyFactory.getAddTargetValueStatus(reportTemplateId);
        return addTargetValueStatus.add(reportDataId, data);
    }

    /**
     * 按年或地区查询单个指标的值
     * @param years 年份的集合
     * @param areaName  地区名称
     * @param targetId  指标id值
     * @return  指标的年和值的集合
     */
    @Override
    public List<StatisticVo> getStatisticInfo(List<Integer> years, String areaName, Long targetId) {
        //可以从2000年统计到2100年
        double[] year = new double[100];
        List<StatisticVo> statisticVoList = targetKeyValueMapper.getStatisticInfoById(years, areaName, targetId);
        for (StatisticVo statisticVo : statisticVoList) {
            year[statisticVo.getYear() - 2000] += statisticVo.getTargetValue();
        }

        List<StatisticVo> result = new ArrayList<>();

        for (int i = 0; i < year.length; i++) {
            if (year[i] != 0) {
                StatisticVo statisticVo = new StatisticVo();
                statisticVo.setYear(2000 + i);
                statisticVo.setTargetValue(year[i]);
                result.add(statisticVo);
            }
        }

        return result;
    }
}
