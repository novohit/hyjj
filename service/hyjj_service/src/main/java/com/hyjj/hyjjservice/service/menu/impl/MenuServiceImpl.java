package com.hyjj.hyjjservice.service.menu.impl;

import com.hyjj.hyjjservice.dao.MenuMapper;
import com.hyjj.hyjjservice.dataobject.Menu;
import com.hyjj.hyjjservice.service.menu.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    private final MenuMapper menuMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public LinkedList<Menu> queryAllMenus() {
        LinkedList<Menu> menus = menuMapper.queryAllMenus();
        Collections.sort(menus, new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return (int)(o1.getId() - o2.getId());
            }
        });
        if (menus == null || menus.isEmpty()) {
            log.info("没有查询到菜单");
        }
        return menus;
    }
}
