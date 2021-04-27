package com.hyjj.hyjjservice.controller.statistic;

import com.hyjj.hyjjservice.controller.statistic.datatransferobject.GetStatisticInfoDTO;
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

    @ApiOperation(value = "添加指标", notes = "审核通过的时候调用此接口，获取报表里面填的<b>数值</>(一定是要数值，如果是其他字符则直接报错，" +
            "如果表里面数据为空则用0来替代传过来)，从左到右以数组的方式传给我（用body来传）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reportDataId", value = "哪张报表的值，传该报表的id过来", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "data", value = "该报表里面的指标，从左到右获取并以数组的方式传过来，不可以传空，如果是空用0来代替", required = true)
    })
    @PostMapping("addTargetKeyValue")
    public CommonReturnType addTargetKeyValue(Long reportDataId, @RequestBody List<Double> data) {
        return statisticService.addTargetKeyValue(reportDataId, data) > 0 ?
                CommonReturnType.ok().add("result", "success") : CommonReturnType.ok().add("result", "fail");
    }

    @ApiOperation(value = "获取统计信息", notes = "根据指标id查询对应指标的值")
    @GetMapping("getStatisticInfo")
    public CommonReturnType getStatisticInfo(GetStatisticInfoDTO getStatisticInfoDTO) {
        return CommonReturnType.ok().add("info", statisticService.getStatisticInfo(
                getStatisticInfoDTO.getYears(), getStatisticInfoDTO.getAreaName(), getStatisticInfoDTO.getTargetId()));
    }
}
