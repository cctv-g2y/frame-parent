package com.makun.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @说明：[日期处理]
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date strToDate(String str, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String nowString() {
        String str1 = "";
        String str2 = "";
        String str3 = "";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String format = DateUtils.format(new Date(), pattern);
        String[] split = format.split(" ");
        String[] split1 = split[0].split("-");
        for (String string : split1) {
            str1 += string;
        }
        String[] split2 = split[1].split(":");
        for (String string : split2) {
            str2 += string;
        }
        str3 = str1 + str2;
        for (int i = 0; i < 4; i++) {
            str3 += (int) (Math.random() * 10) + "";
        }
        return str3;
    }

}
