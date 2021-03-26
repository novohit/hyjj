package com.hyjj.hyjjservice.controller.statistic;

import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistic")
@Api(tags = "统计相关接口")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @ApiOperation("根据parent_id查找指标")
    @ApiImplicitParam(name = "parentId", value = "上级id，如果没有则为0", required = true, dataTypeClass = Long.class)
    @GetMapping("target")
    public CommonReturnType getStatisticTargetKey(Long parentId) {
        return CommonReturnType.ok().add("statistic", statisticService.getStatisticTargetKey(parentId));
    }
}
