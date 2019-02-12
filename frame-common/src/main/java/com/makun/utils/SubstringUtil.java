package com.makun.utils;

import org.springframework.util.StringUtils;

/**
 * @说明：字符串截取类
 * @author makun
 */
public class SubstringUtil {

    public static String getFilePostfix(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
