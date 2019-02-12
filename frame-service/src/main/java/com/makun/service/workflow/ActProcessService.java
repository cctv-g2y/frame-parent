package com.makun.service.workflow;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.springframework.web.multipart.MultipartFile;

import com.makun.utils.QueryUtil;
import com.makun.utils.R;

/**
 * @说明:流程定义服务层
 * @author makun
 */
public interface ActProcessService {

    /**
     * @说明:流程定义列表数据
     */
    public Map<String, Object> processList(QueryUtil query);

    /**
     * @说明:运行中的实例列表数据
     */
    public Map<String, Object> runningList(QueryUtil query);

    /**
     * @说明:通过部署ID读取资源
     * @param processDefinitionId 流程定义ID
     * @param processInstanceId   流程实例ID
     * @param resourceType        资源类型(xml|image)
     */
    public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception;

    /**
     * @说明:设置流程分类
     */
    public R updateCategory(String procDefId, String category);

    /**
     * @说明:部署流程 - 保存
     */
    public R deploys(String exportDir, String category, MultipartFile file);

    /**
     * @说明:挂起、激活流程实例
     */
    public R updateState(String state, String procDefId);

    /**
     * @说明:将部署的流程转换为模型
     */
    public R convertToModel(String procDefId) throws UnsupportedEncodingException, XMLStreamException;

    /**
     * @说明:导出图片文件到硬盘
     */
    public List<String> exportDiagrams(String exportDir, String procDefId) throws IOException;

    /**
     * @说明:删除部署的流程，级联删除流程实例
     */
    public R deleteDeployment(String[] deploymentIds);

    /**
     * @说明:删除部署的流程实例
     */
    public R deleteProcIns(String[] procInsIds, String deleteReason);

}
