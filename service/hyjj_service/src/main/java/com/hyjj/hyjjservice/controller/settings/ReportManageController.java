package com.hyjj.hyjjservice.controller.settings;

import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.FormulaListVO;
import com.hyjj.hyjjservice.dataobject.Formula;
import com.hyjj.hyjjservice.dataobject.Gdp;
import com.hyjj.hyjjservice.service.settings.ReportManageService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "设置中的报表管理")
@RequestMapping("/settings/report")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
public class ReportManageController {

    @Autowired
    private ReportManageService reportManageService;

    @GetMapping("formulaList")
    @ApiOperation("获取公式列表")
    public CommonReturnType formulaList(Integer id,Integer pageNum,Integer pageSize){
        List<FormulaListVO> formulaList = reportManageService.getFormulaList(pageNum,pageSize);
        return CommonReturnType.ok().add("list",formulaList);
    }

    @GetMapping("searchFormula")
    @ApiOperation("搜索公式")
    @ApiImplicitParam(name = "formname",value = "表名", required = true)
    public CommonReturnType searchFormula(String formName,Integer pageNum,Integer pageSize){
        List<FormulaListVO> formulaByFormName = reportManageService.getFormulaByFormName(formName,pageNum,pageSize);
        return CommonReturnType.ok().add("list",formulaByFormName);

    }

    @GetMapping("reportTemplateList")
    @ApiOperation("报表列表")
    public CommonReturnType reportTemplateList(ReportTemplateVO reportTemplateVO){
        System.out.println(reportTemplateVO);
        return CommonReturnType.ok().add("list",reportManageService.getReportTemplateList(reportTemplateVO));
    }

    @GetMapping("getComInfoList")
    public CommonReturnType getComInfoList(Integer pageNum,Integer pageSize){
        return CommonReturnType.ok().add("list",reportManageService.getComInfoList(pageNum,pageSize));
    }

    @GetMapping("getFillReport")
    public CommonReturnType getFillReport(Long id){
        return CommonReturnType.ok().add("neededFillList",reportManageService.getNeededFillList(id)).add("notNeededFillList",reportManageService.getNotNeededFillList(id));

    }

    @PostMapping("setFillReport")
    public CommonReturnType setFillReportList(@RequestBody Map<String,Object> mapParam){
        Long id = Long.parseLong(mapParam.get("id").toString());
        String reportIds = mapParam.get("reportIds").toString();
        return CommonReturnType.ok().add("success",reportManageService.setFillReportList(id, reportIds));
    }

    @GetMapping("getReportTemplateInfo")
    public CommonReturnType getReportTemplateInfo(Integer id){
        return CommonReturnType.ok().add("info",reportManageService.getReportTemplateInfo(id));
    }

    @GetMapping("getCurrentYearData")
    public CommonReturnType getCurrentYearData(Integer pageNum,Integer pageSize){
        return CommonReturnType.ok().add("GdpData",reportManageService.getCurrentYearData(pageNum,pageSize));
    }

    @GetMapping("getPassYearData")
    public CommonReturnType getPassYearData(Integer pageNum,Integer pageSize){
        return CommonReturnType.ok().add("GdpData",reportManageService.getPassYearData(pageNum,pageSize));
    }

    @GetMapping("searchGdpData")
    public CommonReturnType searchGdpData(String district,String year,Integer pageNum,Integer pageSize){
        return CommonReturnType.ok().add("GdpData",reportManageService.searchGdpData(district, year,pageNum,pageSize));
    }

    @PostMapping("updateGdpData")
    public CommonReturnType updateGdpData(@RequestBody Gdp gdpObj){
        return CommonReturnType.ok().add("success",reportManageService.updateGdpData(gdpObj));
    }

    @GetMapping("getGdpDataById")
    public CommonReturnType getGdpDataById(Integer id){
        return CommonReturnType.ok().add("gdpData",reportManageService.getGdpDataById(id));
    }

    @DeleteMapping("deleteGdpData")
    public CommonReturnType deleteGdpData(Integer id){
        return CommonReturnType.ok().add("success",reportManageService.deleteGdpDataById(id));
    }

    @PostMapping("addGdpData")
    public CommonReturnType addGdpData(@RequestBody Gdp gdpObj){
        return CommonReturnType.ok().add("success",reportManageService.insertGdpDataById(gdpObj));
    }

//    @GetMapping("manualCreateReport")
//    public CommonReturnType manualCreateReport(){
//
//    }
}
