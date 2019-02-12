package com.makun.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @说明：分页数据格式化显示工具类
 * @author makun
 *
 */
public class PageUtil {

    /**
     * @说明：获取分页对象
     */
    public static <T> Page<T> getStartPage(Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        Page<T> startPage = PageHelper.startPage(query.getPage(), query.getLimit(), true);
        return startPage;
    }

    /**
     * @说明：格式化数据
     */
    public static <T> Map<String, Object> bootstarpTableData(Page<T> startPage, List<T> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", startPage.getTotal());
        map.put("rows", list);
        return map;
    }

}
