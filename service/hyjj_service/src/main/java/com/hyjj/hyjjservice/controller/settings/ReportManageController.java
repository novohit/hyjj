package com.hyjj.hyjjservice.controller.settings;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.FormulaListVO;
import com.hyjj.hyjjservice.dataobject.Formula;
import com.hyjj.hyjjservice.dataobject.Gdp;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.settings.ReportManageService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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
    public CommonReturnType formulaList(Integer pageNum,Integer pageSize){
        List<FormulaListVO> formulaList = reportManageService.getFormulaList(pageNum,pageSize);
        return CommonReturnType.ok().add("list",formulaList);
    }

    @GetMapping("getFormulaListSum")
    public CommonReturnType getFormulaListSum(){
        int data = reportManageService.getFormulaListSum();
        return CommonReturnType.ok().add("sum",data);
    }


    @GetMapping("reportTemplateList")
    @ApiOperation("报表列表")
    public CommonReturnType reportTemplateList(ReportTemplateVO reportTemplateVO){
        return CommonReturnType.ok().add("list",reportManageService.getReportTemplateList(reportTemplateVO));
    }

    @GetMapping("getReportTemplateListSum")
    public CommonReturnType getReportTemplateListSum(ReportTemplateVO reportTemplateVO){
        int data = reportManageService.getReportTemplateListSum(reportTemplateVO);
        return CommonReturnType.ok().add("sum",data);
    }

    @GetMapping("getComInfoList")
    public CommonReturnType getComInfoList(String name,Integer pageNum,Integer pageSize){
        return CommonReturnType.ok().add("list",reportManageService.getComInfoList(name,pageNum,pageSize));
    }

    @GetMapping("getComInfoListSum")
    public CommonReturnType getComInfoListSum(String name){
        return CommonReturnType.ok().add("sum",reportManageService.getComInfoListSum(name));
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

    @GetMapping("getCurrentYearDataSum")
    public CommonReturnType getCurrentYearDataSum(){
        int data = reportManageService.getCurrentYearDataSum();
        return CommonReturnType.ok().add("sum",data);
    }

    @GetMapping("getPassYearData")
    public CommonReturnType getPassYearData(Integer pageNum,Integer pageSize){
        return CommonReturnType.ok().add("GdpData",reportManageService.getPassYearData(pageNum,pageSize));
    }

    @GetMapping("getPassYearDataSum")
    public CommonReturnType getPassYearDataSum(){
        int data = reportManageService.getPassYearDataSum();
        return CommonReturnType.ok().add("sum",data);
    }

    @GetMapping("searchGdpData")
    public CommonReturnType searchGdpData(String district,String year,Integer pageNum,Integer pageSize){
        return CommonReturnType.ok().add("GdpData",reportManageService.searchGdpData(district, year,pageNum,pageSize));
    }

    @GetMapping("getSearchGdpDataSum")
    public CommonReturnType getSearchGpaDataSum(String district,String year){
        int data = reportManageService.getSearchGpaDataSum(district,year);
        return CommonReturnType.ok().add("sum",data);
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
        System.out.println(gdpObj.getDistrict());
        return CommonReturnType.ok().add("success",reportManageService.insertGdpDataById(gdpObj));
    }

    @GetMapping("manualCreateReport")
    public CommonReturnType manualCreateReport(String endDate,Long[] ids) throws Exception{
        boolean flag = true;
        for (Long id : ids) {
            if(!reportManageService.manualCreateReport(endDate,id)){
                flag = false;
            }
        }
        return CommonReturnType.ok().add("success",flag);
    }

    @GetMapping("oneKeyCreate")
    public CommonReturnType oneKeyCreate(String endDate) throws Exception{
        boolean b = reportManageService.oneKeyCreateReport(endDate);
        return CommonReturnType.ok().add("success",b);
    }
}
