package com.makun.service.attend.impl;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.attend.AttDateDao;
import com.makun.entity.attend.AttendDate;
import com.makun.service.attend.AttDateService;
import com.makun.utils.R;

/**
 * @说明：[查询所有日期service层]
 * @author makun
 */
@Service
public class AttDateServiceImpl extends ServiceImpl<AttDateDao, AttendDate> implements AttDateService {

    @Autowired
    private AttDateDao attdateDao;

    /**
     * 查询所有日期
     */
    @Override
    public Page<AttendDate> showPageList(Page<AttendDate> page, Map<String, Object> map) {
        page.setRecords(attdateDao.showattDate(page, map));
        return page;
    }

    /**
     * 根据id查询日期详情
     */
    @Override
    public AttendDate getattDate(String attDateid) {

        return attdateDao.selectById(attDateid);
    }

    /**
     * 根据日期查询日期详情
     */
    @Override
    public AttendDate getattDateByDate(String calendarDate) {
        return attdateDao.getDateByDate(calendarDate);
    }

    /**
     * 添加日期
     */
    @Override
    public boolean saveDate(AttendDate attenddate) {
        boolean isSave = false;
        int insert = attdateDao.insert(attenddate).intValue();
        if (insert > 0) {
            isSave = true;
        }
        return isSave;
    }

    /**
     * 根据id修改日期
     */
    @Override
    public boolean upDate(AttendDate attenddate) {
        boolean isUpdate = false;
        int insert = attdateDao.updateById(attenddate).intValue();
        if (insert > 0) {
            isUpdate = true;
        }
        return isUpdate;
    }

    /**
     * 删除日期
     */
    @Override
    @Transactional
    public boolean deleteDate(String[] attDateid) {
        boolean isDel = false;
        int deleteDate = attdateDao.deleteDate(attDateid);
        if (deleteDate > 0) {
            isDel = true;
        }
        return isDel;
    }

    /**
     * 判断是否是工作日
     */
    @Override
    public R isattend(LocalDate workDate) {
        String isattend = attdateDao.isattend(workDate);
        if ("".equals(isattend) || null == isattend) {
            return R.error("当天未定义，请联系管理员");
        } else if (isattend.equals("1")) {
            return R.ok("工作日，请签到");
        } else if (isattend.equals("0")) {
            return R.error("今天休假，不用签到");
        } else {
            return R.error("未知错误");
        }
    }

}
