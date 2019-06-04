package com.nyist.hive.udf.timeudf;

/**
 * @Author: skm
 * @Date: 2019/5/31 16:45
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */

import com.nyist.hive.udf.util.TimeUtil;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 给定一个日期的毫秒数，转换成想要的格式化时间形式
 */
public class FormatTimeUdf extends UDF {

    /**
     * 传入long类型的毫秒时间，转换成格式化时间
     * @param ms
     * @param format
     * @return
     */
    public String evaluate(long ms, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(ms);

        return simpleDateFormat.format(date);

    }

    /**
     * 传入字符类型的毫秒时间，转换为指定的格式化时间形式
     * @param ms
     * @param format
     * @return
     */
    public String evaluate(String ms, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(Long.parseLong(ms));

        return simpleDateFormat.format(date);

    }

    /**
     *
     * @param ms
     * @param format
     * @param week      用于区分其他的方法，表示该方法是将时间格式化为本周的第一天
     * @return
     */
    public String evaluate(long ms,String format, int week){
        Date date = new Date();
        date.setTime(ms);

        //获得当前时间所在周的第一天
        Date beginWeek = TimeUtil.getBeginWeek(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(beginWeek);
    }

    /**
     *
     * @param ms
     * @param format
     * @param week      用于区分其他的方法，表示该方法是将时间格式化为本周的第一天
     * @return
     */
    public String evaluate(String ms,String format, int week){
        Date date = new Date();
        date.setTime(Long.parseLong(ms));

        //获得当前时间所在周的第一天
        Date beginWeek = TimeUtil.getBeginWeek(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(beginWeek);
    }

    /**
     *
     * @param ms
     * @param format
     * @param month     此处的String字段用以区分其他方法，表示该方法对时间格式化后取的是当前月的第一天
     * @return
     */
    public String evaluate(long ms,String format, String month){
        Date date = new Date();
        date.setTime(ms);

        //获得当前时间所在月的第一天
        Date beginMonth = TimeUtil.getBeginMonth(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(beginMonth);

    }

    /**
     *
     * @param ms
     * @param format
     * @param month     此处的String字段用以区分其他方法，表示该方法对时间格式化后取的是当前月的第一天
     * @return
     */
    public String evaluate(String ms,String format, String month){
        Date date = new Date();
        date.setTime(Long.parseLong(ms));

        //获得当前时间所在月的第一天
        Date beginMonth = TimeUtil.getBeginMonth(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(beginMonth);

    }



}
