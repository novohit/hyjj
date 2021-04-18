package com.hyjj.hyjjservice.controller.statistic;

import com.hyjj.hyjjservice.service.statistic.StatisticService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "添加指标", notes = "要存的<b>指标</b>的<b>json</b>格式字符串（可以通过查询指标id来查询，这些指标的上一级指标的名字就是报表的标题，" +
            "设置该json的key为指标id，值为该指标对应的值），在审核通过的时候调用这个接口。" +
            "<br/><b>tips：</b>如果不这样做的话后台会很麻烦且效率低还容易搞崩服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reportDataId", value = "哪张报表的值，传该报表的id过来", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "json", value = "需要统计的指标，以json字符串的形式发给我", required = true, example = "{\"1\":\"100\"}")
    })
    @PostMapping("addTargetKeyValue")
    public CommonReturnType addTargetKeyValue(Long reportDataId, @RequestBody List<Double> data) {
        statisticService.addTargetKeyValue(reportDataId,data);
        return CommonReturnType.ok().add("result","success");
    }

    @ApiOperation(value = "获取统计信息", notes = "如果要查看合计的话直接传二级指标的id过来就可以，前提是这个二级指标所对应的三级指标的单位一定要相同")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "years", value = "年份的集合", required = true, example = "[\"2020\",\"2021\"]"),
            @ApiImplicitParam(name = "areaName", value = "地区的名称", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "targetId", value = "指标的id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "count", value = "为提升效率，还需要传一个boolean类型来判断是否统计合计", required = true, dataTypeClass = Boolean.class)
    })
    @GetMapping("getStatisticInfo")
    public CommonReturnType getStatisticInfo(@RequestBody List<Integer> years, String areaName, Long targetId, Boolean count) {
        return CommonReturnType.ok().add("info", statisticService.getStatisticInfo(years, areaName, targetId, count));
    }
}
