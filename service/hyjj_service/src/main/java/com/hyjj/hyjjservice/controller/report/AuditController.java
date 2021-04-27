package com.hyjj.hyjjservice.controller.report;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.hyjjservice.service.user.UserRoleService;
import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("audit")
@Api(tags = "审核相关的接口")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
public class AuditController {

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

    @GetMapping("detailReport")
    @ApiOperation("获取报表的详情信息")
    @ApiImplicitParam(name = "reportId", value = "报表id", required = true, dataTypeClass = String.class)
    public CommonReturnType getDetailReport(Long reportId) {
        return CommonReturnType.ok().add("detailReport", auditService.getDetailReport(reportId));
    }

    @PutMapping("report")
    @ApiOperation("审核报表")
    @GetUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reportId", value = "报表id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "judge", value = "审核结果，0为不通过，1为通过", required = true, dataTypeClass = Integer.class)
    })
    public CommonReturnType auditReport(Long reportId, Integer judge) {
        User user = threadLocal.get();
        return CommonReturnType.ok().add("result", auditService.auditReport(reportId, judge, user));
    }

    @PutMapping("batchReport")
    @ApiOperation("批量审核报表")
    @GetUser
    public CommonReturnType auditReport(@RequestBody Map<Long,Integer> map) {
        System.out.println(map);
        User user = threadLocal.get();
        return CommonReturnType.ok().add("result", auditService.batchAuditReport(map, user));
    }

    @GetMapping("company")
    @ApiOperation("获取单位信息")
    public CommonReturnType getCompany() {
        return CommonReturnType.ok().add("company", auditService.selectAllCompany());
    }
}
