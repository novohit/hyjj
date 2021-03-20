package com.hyjj.hyjjservice.controller.index;

import com.hyjj.hyjjservice.dataobject.Menu;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hyjj.hyjjservice.service.menu.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    private final MenuService menuService;

    public IndexController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value="获取菜单列表", notes="获取菜单列表，目前实现为返回所有菜单")
    @GetMapping("/menu")
    CommonReturnType queryAllMenus() {
        List<Menu> menus = menuService.queryAllMenus();
        return CommonReturnType.ok().add("menu",menus);
    }
}
