package com.hyjj.hyjjservice.controller.report;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.fill.util.FileUtil;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditDto;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("audit")
@Api(tags = "审核相关的接口")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
public class AuditController {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private AuditService auditService;

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> threadLocal;

    @GetMapping("industry")
    @ApiOperation("获取行业信息")
    public CommonReturnType getIndustry() {
        return CommonReturnType.ok().add("industry", auditService.getIndustry());
    }

    @GetMapping("statement")
    @ApiOperation("查看需要审核的报表")
    @GetUser
    public CommonReturnType getStatement(AuditVO auditVO,
                                         @RequestParam(required = false, defaultValue = "1") int pageNum,
                                         @RequestParam(required = false, defaultValue = "10") int pageSize) throws Exception {
        User user = threadLocal.get();
        return CommonReturnType.ok().add("reportData", auditService.getStatement(auditVO, user, pageNum, pageSize));
    }

    @GetMapping("statementSum")
    @ApiOperation("查看总记录条数")
    @GetUser
    public CommonReturnType getStatementSum(AuditVO auditVO){
        User user = threadLocal.get();
        return CommonReturnType.ok().add("reportData", auditService.getStatementSum(auditVO, user));
    }

    @PutMapping("report")
    @ApiOperation("审核报表")
    @GetUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reportId", value = "报表id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "judge", value = "审核结果，0为不通过，1为通过", required = true, dataTypeClass = Integer.class)
    })
    public CommonReturnType auditReport(@RequestBody AuditDto auditDto) {
        User user = threadLocal.get();
        return CommonReturnType.ok().add("result", auditService.auditReport(auditDto.getReportId(), auditDto.getJudge(), auditDto.getTailHtml(), user));
    }

    @PutMapping("batchReport")
    @ApiOperation("批量审核报表")
    @GetUser
    public CommonReturnType auditReport(@RequestBody Map<Long,Integer> map) {
        User user = threadLocal.get();
        return CommonReturnType.ok().add("result", auditService.batchAuditReport(map, user));
    }

    @GetMapping("company")
    @ApiOperation("获取单位信息")
    public CommonReturnType getCompany() {
        return CommonReturnType.ok().add("company", auditService.selectAllCompany());
    }


    @GetMapping("download")
    public void download(@RequestParam String values, String filename, HttpServletResponse response, Integer reportId) throws Exception {

        String[] split = values.split(",");

        System.out.println(split.length);
        System.out.println(filename);
        System.out.println(values);
        System.out.println(reportId);
        ReportTemplate reportTemplate = auditService.getAllValueRowAndCol(reportId);
        System.out.println(reportTemplate.getAllValueCol());
        System.out.println(reportTemplate.getAllValueRow());
        fileUtil.setExcelValue(reportTemplate,split,filename,response);
    }
}
