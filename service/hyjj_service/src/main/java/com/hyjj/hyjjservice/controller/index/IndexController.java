package com.hyjj.hyjjservice.controller.index;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.dataobject.Menu;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hyjj.hyjjservice.service.menu.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    private final MenuService menuService;

    @Autowired
    private AuditService auditService;

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

    @ApiOperation(value = "获取当前用户待审核列表")
    @GetMapping("waitToAudit")
    @GetUser
    public CommonReturnType checkPending() {
        return CommonReturnType.ok().add("reports", auditService.getStatement(1, threadLocal.get()));
    }

    @ApiOperation(value = "获取当前用户本周已审核列表")
    @GetMapping("alreadyAuditInWeek")
    @GetUser
    public CommonReturnType alreadyAuditInWeek() {
        return CommonReturnType.ok().add("reports", auditService.getStatement(2, threadLocal.get()));
    }

    @ApiOperation(value = "获取当前用户本月已审核列表")
    @GetMapping("alreadyAuditInMonth")
    @GetUser
    public CommonReturnType alreadyAuditInMonth() {
        return CommonReturnType.ok().add("reports", auditService.getStatement(3, threadLocal.get()));
    }

    @ApiOperation(value = "获取当前用户累计列表")
    @GetMapping("alreadyAuditTotal")
    @GetUser
    public CommonReturnType alreadyAuditTotal() {
        return CommonReturnType.ok().add("reports", auditService.getStatement(4, threadLocal.get()));
    }
}
