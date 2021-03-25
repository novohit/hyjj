package com.hyjj.hyjjservice.controller.fill;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.fill.util.FileUtil;
import com.hyjj.hyjjservice.dataobject.ReportDataHtml;
import com.hyjj.hyjjservice.dataobject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.fill.FillService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.List;

@RestController
@Api(tags = "填报数据相关")
@RequestMapping("report")
public class FillController {

    @Autowired
    private FillService fillService;

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> threadLocal;

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
    public CommonReturnType saveReportDataHtml(ReportDataHtml reportDataHtml) {
        Integer i = fillService.saveReportDataHtml(reportDataHtml);
        return i.equals(1) ? CommonReturnType.ok() : CommonReturnType.error().setErrMsg("保存失败");
    }


    @PutMapping("submit")
    @ApiOperation("填报界面提交按钮")
    public CommonReturnType submitReport(ReportDataHtml reportDataHtml) {
        Integer j = fillService.submitReportData(reportDataHtml);
        return j.equals(1) ? CommonReturnType.ok() : CommonReturnType.error();
    }

    @PostMapping("upload")
    @ResponseBody
    public CommonReturnType upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return CommonReturnType.error().setErrMsg("文件为空");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/Users/ray/uploadtest";
        File dest = new File(filePath + "/" + fileName);
        try {
            file.transferTo(dest);
            return CommonReturnType.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonReturnType.error().setErrMsg("上传失败");
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
    @ApiImplicitParam(name = "fileName", value = "报表的名称", required = true, dataTypeClass = String.class)
    public void download(String fileName) throws IOException {
        String filename = fileName + ".xlsx";
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type", type);
        // 设置编码
        String encode = new String(filename.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + encode);
        FileUtil.download(filename, response);
    }
}
