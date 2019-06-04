package com.nyist.hive.udf.timeudf;

import com.nyist.hive.udf.util.TimeUtil;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: skm
 * @Date: 2019/5/29 18:09
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */
public class BeginWeekUdf extends UDF {
    /**
     * 计算当前时间的周起始时间，也就是那天的凌晨
     * 参数：时间对象
     */
    public static long evaluate(Date date) {
        return TimeUtil.getBeginWeek(date).getTime();
    }

    /**
     * 计算当前时间的周起始时间，也就是那天的凌晨
     * 参数：无参
     */
    public long evaluate() {
        return evaluate(new Date());
    }

    /**
     * 计算当前时间的指定偏移量的周起始时间
     * 参数：offsetWeek
     */
    public long evaluate(int offsetWeek) {
        return evaluate(TimeUtil.getBeginWeek(new Date(), offsetWeek));
    }

    /**
     * 计算指定对象时间,指定偏移量的周的起始时间
     * 参数：offsetDay，date
     */
    public long evaluate(Date date, int offsetWeek) {
        return evaluate(TimeUtil.getBeginWeek(date, offsetWeek));
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
     * 参数：dateStr   时间格式的字符串 ,offsetWeek
     */
    public long evaluate(String dateStr, int offsetWeek) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date parseTime = simpleDateFormat.parse(dateStr);
            return evaluate(parseTime, offsetWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算指定时间串，指定格式化形式的周起始时间
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
     * 计算指定时间串，指定格式化形式,指定偏移量的周起始时间
     * 参数：指定的时间串 ， 指定的格式化时间的格式 ， 指定偏移量
     */
    public long evaluate(String dateStr, String format, int offsetWeek) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date parseTime = simpleDateFormat.parse(dateStr);
            return evaluate(parseTime, offsetWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
