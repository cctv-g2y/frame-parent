package com.makun.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @说明：[分页参数工具类] @author: makun
 */
public class QueryUtil extends LinkedHashMap<String, Object> {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    // 当前查询结束下标
    private int offset;

    // 每页条数
    private int limit;

    // 当前页码[因为bootstrap-table服务端分页参数的格式 又要用到别的分页工具 这里反推页码]
    // offset = (page - 1) * limit
    // page = offset/limit+1
    private int page;

    public QueryUtil(Map<String, Object> params) {
        this.putAll(params);
        // 默认0
        int offset = 0;
        int limit = 5;
        if (null != params.get("offset")) {
            offset = Integer.parseInt(params.get("offset").toString());
        }
        if (null != params.get("limit")) {
            limit = Integer.parseInt(params.get("limit").toString());
        }
        this.page = offset / limit + 1;
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
