package com.hyjj.hyjjservice.controller.fill;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.fill.dto.FormulaVerificationDto;
import com.hyjj.hyjjservice.controller.fill.util.FileUtil;
import com.hyjj.hyjjservice.controller.fill.viewObject.*;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.fill.FillService;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "填报数据相关")
@RequestMapping("report")
public class FillController {

    @Autowired
    private FillService fillService;

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> threadLocal;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private AuditService auditService;

    @GetUser
    @GetMapping("list")
    @ApiOperation("获取待填报的报表")
    public CommonReturnType getReportList(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        User user = threadLocal.get();
        Long userId = user.getId();
        List<ReportDataList> reportDataList = fillService.getReportListByUserId(userId, pageNum, pageSize);
        return CommonReturnType.ok().add("data", reportDataList);
    }

    @GetUser
    @GetMapping("listSum")
    @ApiOperation("获取待填报的报表")
    public CommonReturnType getReportListSum() {
        User user = threadLocal.get();
        Long userId = user.getId();
        return CommonReturnType.ok().add("data", fillService.getReportListByUserIdSum(userId));
    }

    @GetUser
    @GetMapping("fill")
    @ApiOperation("点击报表名称，返回报表的html")
    @ApiImplicitParam(name = "id", value = "报表id", required = true, dataTypeClass = Integer.class)
    public CommonReturnType getReportData(Integer id) {
        User user = threadLocal.get();
        long userId = user.getId();
        ReportDataHtml reportDataHtml = fillService.getReportDataHtml(id, userId);
        TableHeadInfo tableHeadInfo = fillService.getTableHeadInfo(id);
        return CommonReturnType.ok().add("data", reportDataHtml).add("tableHeadInfo",tableHeadInfo);
    }

    @PostMapping("save")
    @ApiOperation("填报界面点击保存按钮")
    public CommonReturnType saveReportDataHtml(@RequestBody Map<String, Object> mapParam) {
        ReportDataHtml reportDataHtml = new ReportDataHtml();
        reportDataHtml.setHeadHtml(mapParam.get("headHtml").toString());
        reportDataHtml.setBodyHtml(mapParam.get("bodyHtml").toString());
        reportDataHtml.setTailHtml(mapParam.get("tailHtml").toString());
        reportDataHtml.setId((Integer) mapParam.get("id"));
        Integer i = fillService.saveReportDataHtml(reportDataHtml);
        return i.equals(1) ? CommonReturnType.ok() : CommonReturnType.error();
    }


    @PostMapping("submit")
    @ApiOperation("填报界面提交按钮")
    public CommonReturnType submitReport(@RequestBody Map<String, Object> mapParam) {
        ReportDataHtml reportDataHtml = new ReportDataHtml();
        reportDataHtml.setHeadHtml(mapParam.get("headHtml").toString());
        reportDataHtml.setBodyHtml(mapParam.get("bodyHtml").toString());
        reportDataHtml.setTailHtml(mapParam.get("tailHtml").toString());
        reportDataHtml.setId((Integer) mapParam.get("id"));
        Integer i = fillService.submitReportData(reportDataHtml);
        return i.equals(1) ? CommonReturnType.ok() : CommonReturnType.error();
    }

    @PostMapping("upload")
    public CommonReturnType upload(UploadVO uploadVO) throws IOException {
        if (uploadVO.getFile().isEmpty()) {
            return CommonReturnType.error().setErrMsg("文件为空");
        }
        byte[] bytes = uploadVO.getFile().getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        ReportTemplate reportTemplate = fillService.getRowAndColByReportId(Integer.parseInt(uploadVO.getReportId()));
        List<Object> cellList = fileUtil.getCellList(reportTemplate, is);
        return CommonReturnType.ok().add("value", cellList);
    }

    @DeleteMapping("clear")
    @ApiOperation("填报界面删除按钮")
    @ApiImplicitParam(name = "reportId", value = "报表id", required = true, dataTypeClass = Integer.class)
    public CommonReturnType clearReportData(Integer reportId) {
        Integer i = fillService.clearReportData(reportId);
        return i.equals(1) ? CommonReturnType.ok() : CommonReturnType.error();
    }

    @GetMapping("download")
    @ApiOperation("导出报表的模板表（空表）")
    @ApiImplicitParam(name = "filename", value = "报表的名称", required = true, dataTypeClass = String.class)
    public void download(String filename, HttpServletResponse response) {
        fileUtil.download(filename, response);
    }

    @ApiOperation("传个模板表的id，返回表号过来")
    @ApiImplicitParam(name = "reportTemplateId", value = "模板表的id", required = true, dataTypeClass = Long.class)
    @GetMapping("getReportNumber")
    public CommonReturnType getReportNumber(@RequestBody List<Long> reportTemplateId) {
        return CommonReturnType.ok().add("reportNumber", fillService.getReportNumber(reportTemplateId));
    }

    @GetMapping("statement")
    @GetUser
    public CommonReturnType getStatement(
            @RequestParam(required = false, defaultValue = "1")Integer pageNum,
            @RequestParam(required = false, defaultValue = "10")Integer pageSize,
            ReportVO reportVO) {
        User user = threadLocal.get();
        return CommonReturnType.ok().add("statement", fillService.getStatement(reportVO, user, pageNum,pageSize));
    }

    @GetMapping("statementSum")
    @GetUser
    public CommonReturnType getStatementSum(ReportVO reportVO) {
        User user = threadLocal.get();
        return CommonReturnType.ok().add("statement", fillService.getStatementSum(reportVO, user));
    }

    @PostMapping("formulaVerification")
    public CommonReturnType formulaVerification(@RequestBody FormulaVerificationDto formulaVerificationDto) {
        return CommonReturnType.ok().add("result", fillService.formulaVerification(formulaVerificationDto));
    }

    @GetMapping("industry")
    @ApiOperation("获取行业信息")
    public CommonReturnType getIndustry() {
        return CommonReturnType.ok().add("industry", auditService.getIndustry());
    }


}
