package com.makun.service.workflow;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Model;

import com.makun.utils.QueryUtil;
import com.makun.utils.R;

/**
 * @说明:流程模型服务层
 * @author makun
 */
public interface ActModelService {

    /**
     * @说明:流程模型列表
     */
    public Map<String, Object> modelList(QueryUtil query);

    /**
     * @说明:创建模型
     */
    public Model createModel(String name, String key, String description, String category)
            throws UnsupportedEncodingException;

    /**
     * @说明:删除模型
     */
    public R delete(String[] ids);

    /**
     * @说明:导出模型的xml文件
     */
    public R exports(String id, HttpServletResponse response);

    /**
     * @说明:根据Model部署流程
     */
    public R deploys(String modelId, String userId);

    /**
     * @说明:更改Model分类
     */
    public R updateCategory(String modelId, String category);

}
