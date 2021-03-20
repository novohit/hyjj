package com.hyjj.hyjjservice.dataobject.myEnum;

import org.apache.commons.lang.StringUtils;

public enum ComBusinessStatus {
    STOPPING("其他"), //其他
    DOING("营业"), //营业
    OTHER("停业"), //停业
    ;
    private final String name;

    ComBusinessStatus(String name) {
        this.name = name;
    }
    public static String getNameByOrdinal(int ordinal) {
        return values()[ordinal].name;
    }
    public static String getNameByOrdinal(String ordinal) {
        if (StringUtils.isEmpty(ordinal)) {
            throw new IllegalArgumentException("所传参数ordinal不应该是空串或null");
        }
        int number = ordinal.charAt(0) - '0';
        if (number < values().length && number >= 0) {
            return values()[number].name;
        }
        throw new IllegalArgumentException("BusinessStatus枚举转换错误，ordinal应该为输入0,1,2");
    }
}
