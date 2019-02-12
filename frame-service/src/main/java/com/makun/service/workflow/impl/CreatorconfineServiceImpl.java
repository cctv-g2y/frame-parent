package com.makun.service.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.workflow.CreatorconfineDao;
import com.makun.entity.workflow.Creatorconfine;
import com.makun.service.workflow.CreatorconfineService;

/**
 * @说明：流程发起权限控制service实现
 * @author makun
 */
@Service
public class CreatorconfineServiceImpl extends ServiceImpl<CreatorconfineDao, Creatorconfine>
        implements CreatorconfineService {

    @Autowired
    private CreatorconfineDao creatorconfineDao;

    /**
     * @说明：根据模型id查询控制权限
     */
    @Override
    public Creatorconfine getByModelId(String modelId) {
        List<Creatorconfine> byModelId = creatorconfineDao.getByModelId(modelId);
        if (null == byModelId || byModelId.isEmpty()) {
            return null;
        } else {
            return byModelId.get(0);
        }
    }

    /**
     * @说明：根据模型id删除控制权限
     */
    @Override
    public void delByModelId(String modelId) {
        creatorconfineDao.delByModelId(modelId);
    }

    /**
     * @说明：根据流程定义key查询控制权限
     */
    @Override
    public Creatorconfine getByDefKey(String key) {
        return creatorconfineDao.getByDefKey(key);
    }

}
