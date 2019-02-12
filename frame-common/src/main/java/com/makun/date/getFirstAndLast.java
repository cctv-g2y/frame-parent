package com.makun.date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @说明：向数据库写入指定年份的一年的数据 可以获取到指定年份的第一天和最后一天 并且判断是不是周末
 * @author makun
 */
public class getFirstAndLast {
    // 指定年份
    static int i = 2018;
    // 计数插入的数据数量
    static int t = 0;
    /**
     * 默认日期格式
     */
    public static String DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 测试主方法
     * 
     * @param args
     */
    public static void main(String[] args) {
        /*
         * for (int i = 1951; i < 1960; i++) {
         * System.out.println(formatDate(getYearFirst(i)));
         * System.out.println(formatDate(getYearLast(i))); }
         * 
         * System.out.println(formatDate(getCurrYearFirst()));
         * System.out.println(formatDate(getCurrYearLast()));
         */
        Connection connection = null;
        // 预编译的Statement，使用预编译的Statement提高数据库性能
        PreparedStatement preparedStatement = null;
        // 结果集
        int resultSet;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sprboot?characterEncoding=utf-8",
                    "root", "root");
            List<Date> betweenDates = getBetweenDate(getYearFirst(i), getYearLast(i));
            for (Date date : betweenDates) {
                java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                String dates = formatter.format(date);// 格式化数据
                String is_workingday;
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                        || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    is_workingday = "0";
                } else {
                    is_workingday = "1";
                }
                t++;
                // 定义sql语句 ?表示占位符
                String sql = "INSERT INTO sys_tab_attend_date (id,calendar_date,is_workingday) VALUE (?,?,?);";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, t + "");
                System.out.println(dates);
                preparedStatement.setString(2, dates);
                preparedStatement.setString(3, is_workingday);
                resultSet = preparedStatement.executeUpdate();
                if (resultSet > 0) {
                    System.out.println("***********//true");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(t);
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @return String 日期字符串
     */
    public static String formatDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * 获取当年的第一天
     * 
     * @param year
     * @return Date
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * 
     * @param year
     * @return Date
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * 
     * @param year
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * 
     * @param year
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取两个日期之间的日期
     * 
     * @param begin 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    private static List<Date> getBetweenDate(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);

//        Calendar tempEnd = Calendar.getInstance();
//        tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        tempEnd.setTime(end);
//        while (tempStart.before(tempEnd)) {
//            result.add(tempStart.getTime());
//            tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        }

        while (begin.getTime() <= end.getTime()) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

}
