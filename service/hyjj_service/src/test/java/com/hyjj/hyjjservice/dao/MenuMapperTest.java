package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.HyjjApplication;
import com.hyjj.hyjjservice.dataobject.Menu;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

////创建容器
//@RunWith(SpringRunner.class)
////指定配置文件
//@SpringBootTest(classes = HyjjApplication.class)
//class MenuMapperTest {
//
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    private MenuMapper menuMapper;
//
//    @Test
//    void queryAllMenus() {
//        List<Menu> menus = menuMapper.queryAllMenus();
//        assertNotNull(menus);
//        assertFalse(menus.isEmpty());
//        System.out.println(menus);
//    }
//}