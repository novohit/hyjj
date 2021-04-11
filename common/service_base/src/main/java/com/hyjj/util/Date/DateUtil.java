package com.hyjj.util.Date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat sdf;
    /**
     * 转换成只带年和月
     * @param date
     * @return
     */
    public static String changeDateToStringWithMonth(Date date){
        sdf= new SimpleDateFormat("yyyy年MM月");
        return sdf.format(date);
    }

    /**
     * 转换成只带年月日
     * @param date
     * @return
     */
    public static String changeDateToStringWithDate(Date date){
        sdf= new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
