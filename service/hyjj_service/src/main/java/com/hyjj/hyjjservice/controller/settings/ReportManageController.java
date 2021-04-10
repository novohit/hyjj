package com.hyjj.hyjjservice.controller.settings;

import com.hyjj.hyjjservice.dataobject.Formula;
import com.hyjj.hyjjservice.service.settings.ReportManageService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "设置中的报表管理")
@RequestMapping("/settings/report")
@PreAuthorize("hasRole('ROLE_')")
public class ReportManageController {

    @Autowired
    private ReportManageService reportManageService;

    @GetMapping("formulalist")
    @ApiOperation("获取公式列表")
    public CommonReturnType formulaList(){
        List<Formula> formulaList = reportManageService.getFormulaList();
        return CommonReturnType.ok().add("list",formulaList);
    }

    @GetMapping("searchformula")
    @ApiOperation("搜索公式")
    @ApiImplicitParam(name = "formname",value = "表名", required = true)
    public CommonReturnType searchFormula(String formName){
        List<Formula> formulaByFormName = reportManageService.getFormulaByFormName(formName);
        return formulaByFormName.isEmpty() ? CommonReturnType.ok().add("list",formulaByFormName) : CommonReturnType.error().setErrMsg("未查询到数据");

    }

    @GetMapping("addformula")
    @ApiOperation("新增公式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formula", value = "formula对象", dataTypeClass = Formula.class),
            @ApiImplicitParam(name = "forName", value = "表号", dataTypeClass = String.class)
    })
    public CommonReturnType addFormula(Formula formula, String formName){
        int i = reportManageService.addFormula(formula, formName);
        System.out.println(i);
        return CommonReturnType.ok();
    }
}
