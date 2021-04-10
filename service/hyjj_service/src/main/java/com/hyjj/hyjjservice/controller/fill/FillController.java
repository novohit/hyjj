package com.hyjj.hyjjservice.controller.fill;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.fill.util.FileUtil;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataHtml;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.fill.FillService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@Api(tags = "填报数据相关")
@RequestMapping("report")
public class FillController{

    @Autowired
    private FillService fillService;

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> threadLocal;

    @Autowired
    private FileUtil fileUtil;

    @GetUser
    @GetMapping("list")
    @ApiOperation("获取待填报的报表")
    public CommonReturnType getReportList() {
        User user = threadLocal.get();
        System.out.println(user);
        Long userId = user.getId();
        List<ReportDataList> reportDataList = fillService.getReportListByUserId(userId);
        return CommonReturnType.ok().add("data", reportDataList);
    }

    @GetUser
    @GetMapping("fill")
    @ApiOperation("点击报表名称，返回报表的html")
    @ApiImplicitParam(name = "id", value = "报表id", required = true, dataTypeClass = Integer.class)
    public CommonReturnType getReportData(Integer id) {
        User user = threadLocal.get();
        long userId = user.getId();
        ReportDataHtml reportDataHtml = fillService.getReportDataHtml(id, userId);
        return CommonReturnType.ok().add("data", reportDataHtml);
    }

    @PutMapping("save")
    @ApiOperation("填报界面点击保存按钮")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "head_html",value = "头部html", required = true),
            @ApiImplicitParam(name = "body_html",value = "html body",required = true),
            @ApiImplicitParam(name = "tail_html",value = "尾部html",required = true)
    })
    public CommonReturnType saveReportDataHtml(ReportDataHtml reportDataHtml) {
        Integer i = fillService.saveReportDataHtml(reportDataHtml);
        return i.equals(1) ? CommonReturnType.ok() : CommonReturnType.error().setErrMsg("保存失败");
    }


    @PutMapping("submit")
    @ApiOperation("填报界面提交按钮")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "head_html",value = "头部html", required = true),
            @ApiImplicitParam(name = "body_html",value = "html body",required = true),
            @ApiImplicitParam(name = "tail_html",value = "尾部html",required = true)
    })
    public CommonReturnType submitReport(ReportDataHtml reportDataHtml) {
        Integer j = fillService.submitReportData(reportDataHtml);
        return j.equals(1) ? CommonReturnType.ok() : CommonReturnType.error();
    }

    @PostMapping("upload")
    @ApiOperation("填报界面的导出模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "multipartfile类型", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "reportId", value = "报表id", required = true, dataTypeClass = Integer.class)
    })
    public CommonReturnType upload(MultipartFile file,Integer reportId) throws IOException {
        if (file.isEmpty()) {
            return CommonReturnType.error().setErrMsg("文件为空");
        }
        byte[] bytes = file.getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        ReportTemplate reportTemplate = fillService.getRowAndColByTemplateId(reportId);
        List<Object> cellList = fileUtil.getCellList(reportTemplate, is);
        return CommonReturnType.ok().add("value",cellList);
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
    public void download(String filename, HttpServletResponse response){
        fileUtil.download(filename, response);
    }


}
