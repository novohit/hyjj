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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("audit")
@Api(tags = "审核相关的接口")
public class AuditController {

    @Autowired
    private UserRoleService userRoleService;

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
    public CommonReturnType getStatement(AuditVO auditVO) throws Exception {
        User user = threadLocal.get();
        return checkUser(user) ? CommonReturnType.error(EmBusinessError.USER_DONOT_HVER_PERMISSION)
                : CommonReturnType.ok().add("reportData", auditService.getStatement(auditVO, user));
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
        return checkUser(user) ? CommonReturnType.error(EmBusinessError.USER_DONOT_HVER_PERMISSION)
                : CommonReturnType.ok().add("result", auditService.auditReport(reportId, judge, user));
    }

    @GetMapping("company")
    @ApiOperation("获取单位信息")
    public CommonReturnType getCompany() {
        return CommonReturnType.ok().add("company", auditService.selectAllCompany());
    }

    @GetMapping("urge")
    @ApiOperation("获取催办名单")
    @GetUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年份", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "company", value = "要催办的公司的名称", required = true, dataTypeClass = String.class)
    })
    public CommonReturnType getUrge(String year, String company) {
        User user = threadLocal.get();
        return checkUser(user) ? CommonReturnType.error(EmBusinessError.USER_DONOT_HVER_PERMISSION)
                : CommonReturnType.ok().add("result", auditService.getUrge(year, company));
    }

    public Boolean checkUser(User user) {
        //如果用户不是管理员则返回错误代码
        List<Integer> userRoleIds = userRoleService.selectRoleIdByUserId(user.getId());
        for (Integer userRoleId : userRoleIds)
            if (userRoleId < 3)
                return true;
        return false;
    }
}
