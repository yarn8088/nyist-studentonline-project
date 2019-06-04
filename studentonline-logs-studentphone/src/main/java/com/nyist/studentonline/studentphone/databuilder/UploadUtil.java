package com.nyist.studentonline.studentphone.databuilder;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 模拟手机上报日志程序
 */
public class UploadUtil {

    /**
     * 上传日志
     */
    public static void upload(String json) {
        try {
            //web项目发布地址
            URL url = new URL("http://master:8070/studentonline/coll/index");
            //本地测试使用
//            URL url = new URL("http://localhost:8070/coll/index");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为post
            conn.setRequestMethod("POST");

            //时间头用来供server进行时间校对的，获得的是用户客户端时间
            conn.setRequestProperty("clientTime", System.currentTimeMillis() + "");
            //允许上传数据
            conn.setDoOutput(true);
            //设置请求的头信息,设置内容类型
            conn.setRequestProperty("Content-Type", "application/json");
            //输出流
            OutputStream out = conn.getOutputStream();
            out.write(json.getBytes());
            out.flush();
            out.close();
            int code = conn.getResponseCode();
            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
