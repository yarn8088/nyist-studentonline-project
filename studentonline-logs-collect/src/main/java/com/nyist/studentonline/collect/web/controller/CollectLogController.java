package com.nyist.studentonline.collect.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.nyist.studentonline.logs.common.*;
import com.nyist.studentonline.logs.util.GeoUtil;
import com.nyist.studentonline.logs.util.PropertiesUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: skm
 * @Date: 2019/5/19 17:25
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */
@Controller
@RequestMapping("/coll")
public class CollectLogController {

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public AppLogEntity collect(@RequestBody AppLogEntity entity, HttpServletRequest httpServletRequest) {
        //       1:日志上传进来先进行校对时间
        //接收到日志时候的当前时间,即服务器时间
        long myTime = System.currentTimeMillis();

        //日志时间，即客户端时间
        long clientTime = Long.parseLong(httpServletRequest.getHeader("clientTime"));
        //时间差
        long diff = myTime - clientTime;
        proTime(entity, diff);


        //      2.进行时间校对后对基本属性进行复制

//实体类AppLogEntity里面有些属性是五个日志共有的属性，这些属性可以通过内省机制拷贝给各个日志
        copyBaseProperties(entity);
        //      3.对日志进行地理信息处理


//        String remoteAddr = httpServletRequest.getRemoteAddr();

//我这里只是本地测试，所以ip固定，为保证数据尽量真实性，采用自动随机生成的方法，但是处理ip的方法已经写出
//        System.out.println(remoteAddr);

//        processIp(entity, remoteAddr);


        //将处理后的数据发送给kafka集群
        sendMessages(entity);


        return entity;
    }


    //校对时间
    private void proTime(AppLogEntity e, long dif) {
        for (AppBaseLog log : e.getAppStartupLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + dif);
        }
        for (AppBaseLog log : e.getAppErrorLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + dif);
        }
        for (AppBaseLog log : e.getAppEventLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + dif);
        }
        for (AppBaseLog log : e.getAppPageLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + dif);
        }
        for (AppBaseLog log : e.getAppUsageLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + dif);
        }
    }

    //复制基本属性
    private void copyBaseProperties(AppLogEntity e) {
        PropertiesUtil.copyProperties(e, e.getAppStartupLogs());
        PropertiesUtil.copyProperties(e, e.getAppEventLogs());
        PropertiesUtil.copyProperties(e, e.getAppErrorLogs());
        PropertiesUtil.copyProperties(e, e.getAppPageLogs());
        PropertiesUtil.copyProperties(e, e.getAppUsageLogs());
    }

    //处理地理位置信息，对地理位置使用缓存的方法使得查询效率更高
    private Map<String, GeoInfo> cache = new HashMap<String, GeoInfo>();

    private void processIp(AppLogEntity entity, String remoteAddr) {
        GeoInfo geoInfo = cache.get(remoteAddr);
        if (geoInfo == null) {
            geoInfo = new GeoInfo();
            geoInfo.setCountry(GeoUtil.getCountry(remoteAddr));
            geoInfo.setProvince(GeoUtil.getProvince(remoteAddr));
            cache.put(remoteAddr, geoInfo);
        }
        for (AppStartupLog appStartupLog : entity.getAppStartupLogs()) {
            appStartupLog.setCountry(geoInfo.getCountry());
            appStartupLog.setProvince(geoInfo.getProvince());
            appStartupLog.setIpAddress(remoteAddr);
        }
    }
/**
 * kafka老版本的api
 */

//    public void sendMessage(AppLogEntity e) {
//        //创建配置对象
//        Properties props = new Properties();
//        props.put("metadata.broker.list", "master:9092");
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("request.required.acks", "1");
//
//        //创建生产者
//        Producer<Integer, String> producer = new Producer<Integer, String>(new ProducerConfig(props));
//        sendSingleLog(producer,Constants.TOPIC_APP_STARTUP,e.getAppStartupLogs());
//        sendSingleLog(producer,Constants.TOPIC_APP_ERROR,e.getAppErrorLogs());
//        sendSingleLog(producer,Constants.TOPIC_APP_EVENT,e.getAppEventLogs());
//        sendSingleLog(producer,Constants.TOPIC_APP_PAGE,e.getAppPageLogs());
//        sendSingleLog(producer,Constants.TOPIC_APP_USAGE,e.getAppUsageLogs());
//
//        //发送消息
//        producer.close();
//    }
//
    /**
     * 发送单个的log消息给kafka
     */
//    private void sendSingleLog(Producer<Integer, String> producer,String topic , AppBaseLog[] logs){
//        for (AppBaseLog log : logs) {
//            String logMsg = JSONObject.toJSONString(log);
//            //创建消息
//            KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, logMsg);
//            producer.send(data);
//        }
//    }

    /**
     * kafka新版本的api
     *
     * @param entity
     */
    //将消息发送个给kafka
    private void sendMessages(AppLogEntity entity) {

        //首先创建kafka的配置对象
        Properties properties = new Properties();
        //  1. 给配置对象添加必要的属性、
        properties.put("bootstrap.servers", "master:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //kafka的ack机制，0,1，-1,all，延迟性越来越高，持久性越来越可靠
        properties.put("acks", "1");


        // 2. 创建生产者
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        //发送消息
        sendSingleMess(producer, Constants.TOPIC_APP_STARTUP, entity.getAppStartupLogs());
        sendSingleMess(producer, Constants.TOPIC_APP_ERROR, entity.getAppErrorLogs());
        sendSingleMess(producer, Constants.TOPIC_APP_EVENT, entity.getAppEventLogs());
        sendSingleMess(producer, Constants.TOPIC_APP_PAGE, entity.getAppPageLogs());
        sendSingleMess(producer, Constants.TOPIC_APP_USAGE, entity.getAppUsageLogs());

        producer.close();

    }

    /**
     * 发送log给kafka的方法
     */
    private static void sendSingleMess(Producer<String, String> produce, String topic, AppBaseLog[] log) {

        for (AppBaseLog log1 : log) {
            String s = JSONObject.toJSONString(log1);
            //创建消息
            ProducerRecord<String, String> stringStringProducerRecord = new ProducerRecord<String, String>(topic, s);
            //发送消息
            produce.send(stringStringProducerRecord);
            // 发完一条线程休息
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }


}
