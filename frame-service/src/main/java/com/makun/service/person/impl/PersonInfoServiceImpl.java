package com.makun.service.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.person.PersonInfoDao;
import com.makun.entity.person.PersonInfo;
import com.makun.service.person.PersonInfoService;

/**
 * @说明：个人资料服务实现层
 * @author makun
 */
@Service
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoDao, PersonInfo> implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;

    /**
     * 根据id查询个人资料
     */
    @Override
    @Cacheable(value = "PersonInfo", key = "#id")
    public PersonInfo getPersonInfo(String id) {
        return personInfoDao.selectById(id);
    }

    /**
     * 个人资料保存
     */
    @Override
    @CacheEvict(value = "PersonInfo", key = "#personInfo.getPersonId()")
    public boolean savePersonInfo(PersonInfo personInfo) {
        int issave = personInfoDao.insert(personInfo).intValue();
        if (issave > 0) {
            return true;
        }
        return false;
    }

    /**
     * 个人资料修改
     */
    @Override
    @CacheEvict(value = "PersonInfo", key = "#personInfo.getPersonId()")
    public boolean updatePersonInfo(PersonInfo personInfo) {
        int isupdate = personInfoDao.updateById(personInfo).intValue();
        if (isupdate > 0) {
            return true;
        }
        return false;
    }

}
