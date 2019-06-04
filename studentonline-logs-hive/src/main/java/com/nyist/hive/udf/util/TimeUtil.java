package com.nyist.hive.udf.util;

/**
 * @Author: skm
 * @Date: 2019/5/29 8:08
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取指定时间的工具类
 */
public class TimeUtil {

    /**
     * 获取当天的凌晨时刻
     */
    public static Date getBeginDay(Date date) {
        //创建格式化时间的对象，并且传参想要的格式化效果,下面的写法会默认获得当下时间的00:00:00时刻
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parseDate = simpleDateFormat.parse(simpleDateFormat.format(date));
            return parseDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 需求：获取指定的前几天或者未来几天的某天的起始凌晨时刻，我们需要在原来的基础上添加方法的参数表示天数的偏移量 offset
     * <p>
     * 该方法获取        指定天数的日期凌晨时刻的时间对象
     */
    public static Date getBeginDay(Date date, int offsetDay) {
        try {

            //获取当前时间的凌晨时刻
            Date beginTime = getBeginDay(date);
            //创建日历对象类
            Calendar calendar = Calendar.getInstance();
            //将当前时间的凌晨时刻传入
            calendar.setTime(beginTime);
            //将日历从当前时间的基础上翻offset天
            calendar.add(Calendar.DAY_OF_MONTH, offsetDay);

            Date calendarTime = calendar.getTime();

            return calendarTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间所在的周的起始凌晨时间
     */
    public static Date getBeginWeek(Date date) {
        try {
            Date beginTime = getBeginDay(date);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(beginTime);
            //通过日历对象获取当前时间在本周属于第几天，例如获得的i的值为3
            // i= 3 则表示当前时间属于本周的第三天，也就是本周的星期二（星期日为当周第一天）。
            int i = calendar.get(Calendar.DAY_OF_WEEK);

            calendar.add(Calendar.DAY_OF_MONTH, -(i - 1));

            Date calendarTime = calendar.getTime();
            return calendarTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * 获得指定偏移量时刻的周起始时间
     */
    public static Date getBeginWeek(Date date, int offsetWeek) {
        try {
            Date beginWeek = getBeginWeek(date);
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(beginWeek);

            calendar.add(Calendar.DAY_OF_MONTH, offsetWeek * 7);

            Date calendarTime = calendar.getTime();

            return calendarTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得当前时间所在的月起始时间
     *
     * 每个月的第一天都是yyyy/MM/01，所以我们只需要对日期进行格式化，格式化默认为当天00:00:00
     */
    public static Date getBeginMonth(Date date) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/01");
            Date parseTime = simpleDateFormat.parse(simpleDateFormat.format(date));
            return parseTime;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得指定偏移量的月起始时间
     */
    public static Date getBeginMonth(Date date, int offsetMonth) {

        try {
            Date beginMonth = getBeginMonth(date);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(beginMonth);

            calendar.add(Calendar.MONTH, offsetMonth);

            Date calendarTime = calendar.getTime();

            return calendarTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



}
