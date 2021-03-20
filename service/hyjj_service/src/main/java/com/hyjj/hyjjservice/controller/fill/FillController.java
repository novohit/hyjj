package com.hyjj.hyjjservice.controller.fill;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.dataobject.ReportDataHtml;
import com.hyjj.hyjjservice.dataobject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.fill.FillService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
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
    public CommonReturnType getReportList() {
        User user = threadLocal.get();
        System.out.println(user);
        Long userId = user.getId();
        List<ReportDataList> reportDataList = fillService.getReportListByUserId(userId);

        return CommonReturnType.ok().add("data", reportDataList);
    }

    @GetUser
    @GetMapping("fill")
    public CommonReturnType getReportData(int id){
        User user = threadLocal.get();
        Long userId = user.getId();
        ReportDataHtml reportDataHtml = fillService.getReportDataHtml(id, userId);
        return CommonReturnType.ok().add("data",reportDataHtml);
    }

    @PutMapping("fill/save")
    public CommonReturnType saveReportDataHtml(ReportDataHtml reportDataHtml){
        Integer i = fillService.saveReportDataHtml(reportDataHtml);
        if(i.equals(1)){
            return CommonReturnType.ok();
        }else{
            return CommonReturnType.error().setErrMsg("保存失败");
        }
    }

//    @PutMapping("fill/submit")
//    public CommonReturnType submitReport(ReportDataHtml reportDataHtml){
//
//    }

    @PostMapping("/upload")
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





}
