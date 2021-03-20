package com.hyjj.hyjjservice.dataobject.myEnum;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComtypeTest {

    @Test
    public void getNameByOrdinalI() {
        String name = Comtype.getNameByOrdinal(0);
        assertEquals("测试\"其他\"枚举", "其他", name);
        name = Comtype.getNameByOrdinal(1);
        assertEquals("测试\"单产业法人单位\"枚举", "单产业法人单位", name);
        name = Comtype.getNameByOrdinal(2);
        assertEquals("测试\"多产业法人单位\"枚举", "多产业法人单位", name);
        name = Comtype.getNameByOrdinal(3);
        assertEquals("测试\"产业活动单位\"枚举", "产业活动单位", name);
        System.out.println("??测试成功一点提示都没有");
    }

    @Test
    public void getNameByOrdinalS() {
        String name = Comtype.getNameByOrdinal("0");
        assertEquals("测试\"其他\"枚举", "其他", name);
        name = Comtype.getNameByOrdinal("1");
        assertEquals("测试\"单产业法人单位\"枚举", "单产业法人单位", name);
        name = Comtype.getNameByOrdinal("2");
        assertEquals("测试\"多产业法人单位\"枚举", "多产业法人单位", name);
        name = Comtype.getNameByOrdinal("3");
        assertEquals("测试\"产业活动单位\"枚举", "产业活动单位", name);
        try {
            name = Comtype.getNameByOrdinal("a");
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        System.out.println("??测试成功一点提示都没有");
    }
}