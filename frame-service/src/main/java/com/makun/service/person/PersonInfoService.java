package com.makun.service.person;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.person.PersonInfo;

/**
 * @说明：个人资料服务层
 * @author makun
 */
public interface PersonInfoService extends IService<PersonInfo> {

    /**
     * 根据id获取个人资料
     */
    public PersonInfo getPersonInfo(String id);

    /**
     * 个人资料保存
     */
    public boolean savePersonInfo(PersonInfo personInfo);

    /**
     * 个人资料修改
     */
    public boolean updatePersonInfo(PersonInfo personInfo);

}
