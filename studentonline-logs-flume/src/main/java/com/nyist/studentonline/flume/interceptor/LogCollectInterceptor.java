package com.nyist.studentonline.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.nyist.studentonline.logs.common.AppBaseLog;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * @Author: skm
 * @Date: 2019/5/25 17:06
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */
public class LogCollectInterceptor implements Interceptor {


    public void initialize() {

    }

    //对事件进行处理，给head上面加上日志创建事件以及日志所属五种中的哪种类型，方便下沉到指定hdfs目录
    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        // 1 .对日志创建时间进行处理
        byte[] bodyJson = event.getBody();
        String s = new String(bodyJson);
        //将字符串转换为日志对象，方便获取日志创建事件字段createdAtMs作为事件的头
        AppBaseLog appBaseLog = JSONObject.parseObject(s, AppBaseLog.class);
        //通过get方法获得createdAtMs字段值
        long time = appBaseLog.getCreatedAtMs();
        //将获得的字段值添加到时间头中
        headers.put("myTimeStamp", Long.toString(time));

        //2.对日志所属的类型进行处理，这里采用是否包含关键字段的方法进行分类、
        String logType = "";
        //pageLog
        if (s.contains("pageId")) {
            logType = "page";
        }
        //eventLog
        else if (s.contains("eventId")) {
            logType = "event";
        }
        //usageLog
        else if (s.contains("singleUseDurationSecs")) {
            logType = "usage";
        }
        //errorLog
        else if (s.contains("errorBrief")) {
            logType = "error";
        }
        //startup
        else if (s.contains("network")) {
            logType = "startup";
        }
        headers.put("logType",logType);

        return null;
    }

    public List<Event> intercept(List<Event> list) {
        for (Event event :list){
            intercept(event);
        }
        return list;
    }

    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        public Interceptor build() {

            return new LogCollectInterceptor();
        }

        public void configure(Context context) {
        }
    }
}
