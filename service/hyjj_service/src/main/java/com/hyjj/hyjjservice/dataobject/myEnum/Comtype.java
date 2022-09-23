package com.hyjj.hyjjservice.dataobject.myEnum;

import org.apache.commons.lang.StringUtils;

public enum Comtype {
    /**
     * 这里顺序不能调换
     * 0: 单产业法人单位
     * 1: 产业活动单位
     * 2: 多产业法人单位
     */
    SINGLE_INDUSTRY("单产业法人单位"),
    INDUSTRY_ACTIVITY("产业活动单位"),
    MULTI_INDUSTRY("多产业法人单位"),
    OTHER("其他");
    private final String name;
    private Comtype(String name) {
        this.name = name;
    }

    /**
     * 根据ordinal（对应数据的值）返回对应的字符串描述
     * @param ordinal 0,1,2,3
     * @return 对应的字符串描述
     */
    public static String getNameByOrdinal(int ordinal) {
        return values()[ordinal].name;
    }
    public static String getNameByOrdinal(String ordinal) {
        if (StringUtils.isEmpty(ordinal)) {
            throw new IllegalArgumentException("所传参数ordinal不应该是空串或null");
        }
        int number = ordinal.charAt(0) - '0';
        //保证只处理符合ordinal取值范围的参数
        if (number < values().length && number >= 0) {
            return values()[number].name;
        }
        throw new IllegalArgumentException("Comtype枚举ordinal格式错误，应该为输入0,1,2,3");
    }
}
