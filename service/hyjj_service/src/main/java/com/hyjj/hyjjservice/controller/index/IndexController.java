package com.hyjj.hyjjservice.controller.index;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.dataobject.Menu;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.hyjjservice.service.user.UserRoleService;
import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hyjj.hyjjservice.service.menu.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "首页相关的接口")
public class IndexController {

    private final MenuService menuService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private UserRoleService userRoleService;

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> threadLocal;

    public IndexController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表，目前实现为返回所有菜单")
    @GetMapping("/menu")
    public CommonReturnType queryAllMenus() {
        List<Menu> menus = menuService.queryAllMenus();
        return CommonReturnType.ok().add("menu", menus);
    }

    @ApiOperation(value = "获取当前用户待审核/填报列表")
    @GetMapping("waitToAudit")
    @GetUser
    public CommonReturnType checkPendingOrFill(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        User user = threadLocal.get();
        return checkUser(user) ? CommonReturnType.ok().add("reports", auditService.getStatement(1, user, false, pageNum, pageSize))
                : CommonReturnType.ok().add("reports", auditService.getStatement(1, user, true, pageNum, pageSize));
    }

    @ApiOperation(value = "获取当前用户本周已审核/填报列表")
    @GetMapping("alreadyAuditInWeek")
    @GetUser
    public CommonReturnType alreadyAuditOrFillInWeek(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        User user = threadLocal.get();
        return checkUser(user) ? CommonReturnType.ok().add("reports", auditService.getStatement(2, user, false, pageNum, pageSize))
                : CommonReturnType.ok().add("reports", auditService.getStatement(2, user, true, pageNum, pageSize));
    }

    @ApiOperation(value = "获取当前用户本月已审核/填报列表")
    @GetMapping("alreadyAuditInMonth")
    @GetUser
    public CommonReturnType alreadyAuditOrFillInMonth(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        User user = threadLocal.get();
        return checkUser(user) ? CommonReturnType.ok().add("reports", auditService.getStatement(3, user, false, pageNum, pageSize))
                : CommonReturnType.ok().add("reports", auditService.getStatement(3, user, true, pageNum, pageSize));
    }

    @ApiOperation(value = "获取当前用户累计审核/填报列表")
    @GetMapping("alreadyAuditTotal")
    @GetUser
    public CommonReturnType alreadyAuditOrFillTotal(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        User user = threadLocal.get();
        return checkUser(user) ? CommonReturnType.ok().add("reports", auditService.getStatement(4, user, false, pageNum, pageSize))
                : CommonReturnType.ok().add("reports", auditService.getStatement(4, user, true, pageNum, pageSize));
    }

    public Boolean checkUser(User user) {
        List<Integer> userRoleIds = userRoleService.selectRoleIdByUserId(user.getId());
        for (Integer userRoleId : userRoleIds) {
            if (userRoleId < 3)
                return true;
        }
        return false;
    }
}
