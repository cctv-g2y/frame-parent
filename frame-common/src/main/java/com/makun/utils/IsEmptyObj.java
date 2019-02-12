package com.makun.utils;

import org.springframework.util.StringUtils;

/**
 * @说明：[判断对象是否为空]
 * @author makun
 */
public class IsEmptyObj {
    public static boolean isempty(Object object) {
        return StringUtils.isEmpty(object);
    }

}
