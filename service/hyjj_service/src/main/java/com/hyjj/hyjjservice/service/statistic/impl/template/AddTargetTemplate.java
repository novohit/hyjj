package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.dataobject.TargetKeyValue;
import com.hyjj.hyjjservice.dataobject.TargetValue;

import java.util.Date;
import java.util.List;

/**
 * 后续优化就是可以加一个方法查询出模板表对应的指标id，目前先写死
 */
public abstract class AddTargetTemplate {

    public Integer add(Long reportDataId, List<Double> data){
        return changeValue(reportDataId, data);
    }

    /**
     * 每张表的样子和要存的值都不一样，因此每张表都要做单独处理
     * @param reportDataId 报表id
     * @param data  报表里面的数据
     * @return  大于0则为成功，否则失败
     */
    public abstract Integer changeValue(Long reportDataId, List<Double> data);

    /**
     * 对每张表进行公式校验
     * @param data 传过来的数据
     * @return 校验是否通过
     */
    public Boolean formulaVerification(List<Long> data){
        return true;
    }

    public Integer addTargetValue(TargetValueMapper targetValueMapper,TargetKeyValueMapper targetKeyValueMapper, Long reportDataId, List<Double> data, List<StatisticsTargetKey> statisticTargetKey){
        Integer result = 0;
        Date date = new Date();
        for (int i = 0; i < statisticTargetKey.size(); i++) {
            TargetValue targetValue = new TargetValue();
            targetValue.setTargetValue(data.get(i));
            targetValue.setGmtModified(date);
            targetValue.setGmtCreate(date);
            targetValue.setIsEffective(0);
            //TODO mapper里面的sql要改,主要是加上个isEffective
            targetValueMapper.insertSelective(targetValue);

            TargetKeyValue targetKeyValue = new TargetKeyValue();
            targetKeyValue.setTargetKeyId(statisticTargetKey.get(i).getId());
            targetKeyValue.setValueId(targetValue.getId());
            targetKeyValue.setGmtModified(date);
            targetKeyValue.setGmtCreate(date);
            targetKeyValue.setReportDataId(reportDataId);
            result += targetKeyValueMapper.insertSelective(targetKeyValue);
        }
        return result;
    }
}
