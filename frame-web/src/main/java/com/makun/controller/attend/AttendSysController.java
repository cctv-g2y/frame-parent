package com.makun.controller.attend;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.makun.config.base.UserUtils;
import com.makun.entity.attend.AttendDate;
import com.makun.entity.attend.AttendInfo;
import com.makun.entity.attend.AttendTime;
import com.makun.service.attend.AttDateService;
import com.makun.service.attend.AttInfoService;
import com.makun.service.attend.AttTimeService;
import com.makun.utils.DateUtils;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;
import com.makun.utils.IsEmptyObj;

/**
 * @说明：考勤控制层
 * @author makun
 */
@RestController
@RequestMapping("/attendSys")
public class AttendSysController {

    @Autowired
    private AttDateService attdateService;

    @Autowired
    private AttTimeService atttimeService;

    @Autowired
    private AttInfoService attinfoService;

    /**
     * 跳转到考勤设置页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/sysattpage")
    public ModelAndView syspagedate() {
        return new ModelAndView("modules/attend/attendSys");
    }

    /**
     * 跳转到个人考勤页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/myattpage")
    public ModelAndView myattpage() {
        return new ModelAndView("modules/attend/attendMy");
    }

    /**
     * 查询考勤时间详情
     * 
     * @return attendTime
     */
    @RequestMapping("/timeInfo")
    public AttendTime getTime() {
        return atttimeService.getTime();
    }

    /**
     * 新增考勤时间
     * 
     * @param attTime
     * @return R
     */
    @RequestMapping("/saveTime")
    public R saveTime(@RequestBody AttendTime attTime) {
        boolean saveTime = atttimeService.saveTime(attTime);
        if (saveTime) {
            return R.ok("保存成功");
        }
        return R.error("保存失败");
    }

    /**
     * 根据id修改考勤时间
     * 
     * @param attTime
     * @return R
     */
    @RequestMapping("/updateTime")
    public R updateTime(@RequestBody AttendTime atime) {
        boolean updaTime = atttimeService.updateTime(atime);
        if (updaTime) {
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    /**
     * 清除考勤时间点的设置
     * 
     * @return R
     */
    @RequestMapping("/flushall")
    public R flushall() {
        atttimeService.flushall();
        return R.ok("清除成功");
    }

    /**
     * 显示考勤日期
     * 
     * @param params
     * @return Page
     */
    @RequestMapping("/showsyslist")
    public Page<AttendDate> showsyslist(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        Page<AttendDate> pageUtil = new Page<AttendDate>(query.getPage(), query.getLimit());

        Page<AttendDate> page = attdateService.showPageList(pageUtil, query);
        return page;
    }

    /**
     * 根据id查询日期详情
     * 
     * @param attDateid
     * @return attendDate
     */
    @RequestMapping("/dateInfo")
    public Map<String, Object> infoById(String attDateid) {
        AttendDate attenddate = attdateService.getattDate(attDateid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("attenddate", attenddate);
        return map;
    }

    /**
     * 添加日期
     * 
     * @param attdate
     * @return R
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttendDate atte) {
        AttendDate attenddate = attdateService.getattDateByDate(DateUtils.format(atte.getCalendarDate()));
        if (!IsEmptyObj.isempty(attenddate)) {
            return R.error("该日期已经存在");
        }
        boolean saveDate = attdateService.saveDate(atte);
        if (saveDate) {
            return R.ok("操作成功");
        }
        return R.error("操作失败");
    }

    /**
     * 根据id修改日期详情
     * 
     * @param atte
     * @return R
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttendDate atte) {
        AttendDate attenddate = attdateService.getattDateByDate(DateUtils.format(atte.getCalendarDate()));
        if (!IsEmptyObj.isempty(attenddate)) {
            if (!(atte.getAttDateid()).equals(attenddate.getAttDateid())) {
                return R.error("该日期已经存在");
            }
        }
        boolean upDate = attdateService.upDate(atte);
        if (upDate) {
            return R.ok("操作成功");
        }
        return R.error("操作失败");
    }

    /**
     * 批量删除日期
     * 
     * @param attDateids
     * @return R
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] attDateids) {
        boolean deleteDate = attdateService.deleteDate(attDateids);
        if (deleteDate) {
            return R.ok("操作成功");
        }
        return R.error("操作失败");
    }

    /**
     * 查看个人签到详情分页列表显示
     * 
     * @param params
     * @return Page
     */
    @RequestMapping("/showmylist")
    public Page<AttendInfo> showmylist(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        Page<AttendInfo> pageUtil = new Page<AttendInfo>(query.getPage(), query.getLimit());
        query.put("userId", UserUtils.getUserId());
        query.put("today", LocalDate.now().minusDays(1));
        Page<AttendInfo> page = attinfoService.getMyattend(pageUtil, query);
        return page;
    }

    /**
     * 判断是否是工作日
     * 
     * @return boolean
     */
    @RequestMapping("/isattend")
    public R isattend() {
        return attdateService.isattend(LocalDate.now());
    }

    /**
     * 查看个人当天签到详情
     * 
     * @return attendInfo
     */
    @RequestMapping("/goattend")
    public AttendInfo goattend() {
        LocalDate now = LocalDate.now();
        String userId = UserUtils.getUserId();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("workDate", now);
        return attinfoService.goattend(map);
    }

    /**
     * 签到
     * 
     * @return attendInfo
     */
    @RequestMapping("/goup")
    public R goup() {
        AttendInfo attendinfo = goattend();
        Date workDate = new Date();
        String format = DateUtils.format(workDate, "HH:mm:ss");
        Date factontimes = DateUtils.strToDate(format, "HH:mm:ss");
        if (IsEmptyObj.isempty(attendinfo)) {
            attendinfo = new AttendInfo();
            AttendTime time = getTime();
            if (IsEmptyObj.isempty(time)) {
                return R.error("考勤时间未定义，请联系管理员");
            } else {
                long shouldontime = time.getOnTime().getTime();
                long factontime = factontimes.getTime();
                long lateTime = ((factontime - shouldontime) / 1000) / 60;
                attendinfo.setUserId(UserUtils.getUserId());
                attendinfo.setWorkDate(workDate);
                if (lateTime <= 0) {
                    attendinfo.setOnStatus("1");
                    attendinfo.setOnTime(factontimes);
                } else {
                    attendinfo.setOnStatus("0");
                    attendinfo.setOnTime(factontimes);
                    attendinfo.setLateTime(lateTime + "");
                }
                if (attinfoService.insert(attendinfo)) {
                    return R.ok("签到成功");
                } else {
                    return R.error("签到失败");
                }
            }
        } else {
            attinfoService.deleteById(attendinfo.getAttendId());
            return R.error("签到失败，请再次尝试");
        }
    }

    /**
     * 签退
     * 
     * @return attendInfo
     */
    @RequestMapping("/godown")
    public R godown() {
        AttendInfo attendinfo = goattend();
        Date workDate = new Date();
        String format = DateUtils.format(workDate, "HH:mm:ss");
        Date factuptimes = DateUtils.strToDate(format, "HH:mm:ss");
        if (!IsEmptyObj.isempty(attendinfo)) {
            AttendTime time = getTime();
            if (IsEmptyObj.isempty(time)) {
                return R.error("考勤时间未定义，请联系管理员");
            } else {
                long shoulduptime = time.getOffTime().getTime();
                long factuptime = factuptimes.getTime();
                long earlyTime = ((factuptime - shoulduptime) / 1000) / 60;
                if (earlyTime >= 0) {
                    attendinfo.setOffStatus("1");
                    attendinfo.setOffTime(factuptimes);
                } else {
                    attendinfo.setOffStatus("0");
                    attendinfo.setOffTime(factuptimes);
                    attendinfo.setEarlyTime(-earlyTime + "");
                }
                if (attinfoService.updateById(attendinfo)) {
                    return R.ok("签退成功");
                } else {
                    return R.error("签退失败");
                }
            }
        } else {
            return R.error("还未签到，不能签退");
        }
    }

    /**
     * 签到意见说明提交
     * 
     * @param attendinfo
     * @return R
     */
    @RequestMapping("/gomark")
    public R gomark(AttendInfo attendinfo) {
        if (attinfoService.updateById(attendinfo)) {
            return R.ok("提交成功");
        } else {
            return R.error("提交失败");
        }
    }

    /**
     * 个人签到详情预览
     * 
     * @param workDate
     * @return attendInfo
     */
    @RequestMapping("/showmyattend")
    public AttendInfo showmyattend(String workDate) {
        String userId = UserUtils.getUserId();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("workDate", workDate);
        return attinfoService.goattend(map);
    }

    /***
     * 考勤详情意见提交
     * 
     * @param attendinfo
     * @return R
     */
    @RequestMapping("/updatemark")
    public R updatemark(@RequestBody AttendInfo attendinfo) {
        if (attinfoService.updateById(attendinfo)) {
            return R.ok("提交成功");
        } else {
            return R.error("提交失败");
        }
    }

    /**
     * 考勤详情意见保存
     * 
     * @param attendinfo
     * @return R
     */
    @RequestMapping("/savemark")
    public R savemark(@RequestBody AttendInfo attendinfo) {
        attendinfo.setUserId(UserUtils.getUserId());
        if (attinfoService.insert(attendinfo)) {
            return R.ok("提交成功");
        } else {
            return R.error("提交失败");
        }
    }

}
