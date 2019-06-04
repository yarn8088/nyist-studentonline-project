package com.nyist.studentonline.logs.util;

/**
 * @Author: skm
 * @Date: 2019/5/24 9:40
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */


import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

/**
 * 地理信息工具类，实现的是通过ip查找地址的区域
 */
public class GeoUtil {
    //创建输入流
    private static InputStream inputStream;
    private static Reader reader;

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            inputStream = classLoader.getResource("GeoLite2-City.mmdb").openStream();
            reader = new Reader(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取国家信息
     */
    public static String getCountry(String ip) {
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("country").get("names").get("zh-CN").textValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未知的国家";

    }

    /**
     * 获取省份信息
     */

    public static String getProvince(String ip) {
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("subdivisions").get(0).get("names").get("zh-CN").textValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未知的省份";

    }

    /**
     * 获得城市信息
     */
    public static String getCity(String ip) {
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("city").get("names").get("zh-CN").textValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未知的城市";
    }


}
