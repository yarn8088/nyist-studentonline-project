package com.nyist.hive.udf.timeudf;

import com.nyist.hive.udf.util.TimeUtil;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: skm
 * @Date: 2019/5/29 10:28
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */
public class BeginDayUdf extends UDF {
    /**
     * 计算当前时间的天起始时间，也就是当天的凌晨
     * 参数：时间对象
     */
    public static long evaluate(Date date) {
        return TimeUtil.getBeginDay(date).getTime();
    }

    /**
     * 计算当前时间的天起始时间，也就是当天的凌晨
     * 参数：无参
     */
    public long evaluate() {
        return evaluate(new Date());
    }

    /**
     * 计算当前时间的指定偏移量的天起始时间
     * 参数：offsetDay
     */
    public long evaluate(int offsetDay) {
        return evaluate(TimeUtil.getBeginDay(new Date(), offsetDay));
    }

    /**
     * 计算指定对象时间的指定偏移量的天起始时间
     * 参数：offsetDay，date
     */
    public long evaluate(Date date, int offsetDay) {
        return evaluate(TimeUtil.getBeginDay(date, offsetDay));
    }

    /**
     * 计算指定时间格式化串的起始时间
     * 参数：dateStr   时间格式的字符串
     */
    public long evaluate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parseTime = simpleDateFormat.parse(dateStr);
            return evaluate(parseTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算指定时间格式化串的起始时间，指定偏移量
     * 参数：dateStr   时间格式的字符串 ,offsetDay
     */
    public long evaluate(String dateStr, int offsetDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parseTime = simpleDateFormat.parse(dateStr);
            return evaluate(parseTime, offsetDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算指定时间串，指定格式化形式的天起始时间
     * 参数：指定的时间串 ， 指定的格式化时间的格式
     */
    public long evaluate(String dateStr, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date parseTime = simpleDateFormat.parse(dateStr);
            return evaluate(parseTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算指定时间串，指定格式化形式,指定偏移量的天起始时间
     * 参数：指定的时间串 ， 指定的格式化时间的格式 ， 指定偏移量
     */
    public long evaluate(String dateStr, String format, int offsetDay) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date parseTime = simpleDateFormat.parse(dateStr);
            return evaluate(parseTime, offsetDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
