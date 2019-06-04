package com.nyist.studentonline.studentphone.databuilder;

import com.alibaba.fastjson.JSONObject;
import com.nyist.studentonline.logs.common.*;
;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 数据生成程序
 */
public class DataBuilder {

    /**
     *
     */


    private static Random random = new Random();
    private static String appId = "sdk34734";//studnetonline应用的id
    private static String[] tenantIds = {"nyist","nyist1","nyist2"};
    private static String[] deviceIds = initDeviceId();
    private static String[] appVersions = {"3.2.1", "3.2.2"};
    private static String[] appChannels = {"OPPO应用商店", "小米应用商店", "华为应用商店", "九游应用商店", "网易应用商店", "果盘应用商店", "vivo应用商店"};
    private static String[] appPlatforms = {"android", "ios"};

    //国家，终端不用上报，服务器自动填充该属性
    private static String[] countrys = {"中国"};
    //省份，终端不用上报，服务器自动填充该属性
    private static String[] provinces = {"上海", "北京", "深圳", "湖南", "河南", "广州", "新疆", "浙江", "未知的省份", "江苏"
    ,"湖北","湖南","武汉","河北","黑龙江","乌鲁木齐","海南","云南","广西","安徽","广东","甘肃","青海","西藏"};
    //网络
    private static String[] networks = {"WiFi", "5G", "4G", "3G"};
    //运营商
    private static String[] carriers = {"中国移动", "中国电信", "中国联通"};
    //机型
    private static String[] deviceStyles = {"iPhone x", "小米8探索版", "华为P30", "oppoFind x", "坚果pro", "oppoa59s", "荣耀v10", "360手机", "努比亚9plus", "诺基亚7"};
    //分辨率
    private static String[] screenSizes = {"2248x1080", "2238x1080"};
    //操作系统
    private static String[] osTypes = {"8.3", "7.1.1", "8.2", "7.1.0"};
    //品牌
    private static String[] brands = {"三星", "华为", "Apple", "魅族", "小米", "OPPO", "坚果", "荣耀", "努比亚", "诺基亚"};
    //事件唯一标识
    private static String[] eventIds = {"popMenu", "autoImport", "BookStore"};
    //事件持续时长
    private static Long[] eventDurationSecsS = {new Long(25), new Long(67), new Long(45)};

    static Map<String, String> map1 = new HashMap<String, String>() {
        {
            put("testparam1key", "testparam1value");
            put("testparam2key", "testparam2value");
        }
    };
    static Map<String, String> map2 = new HashMap<String, String>() {
        {
            put("testparam3key", "testparam3value");
            put("testparam4key", "testparam4value");
        }
    };
    private static Map[] paramKeyValueMapsS = {map1, map2};

    //单次使用时长(秒数),指一次启动内应用在前台的持续时长
    private static Long[] singleUseDurationSecsS = initSingleUseDurationSecs();

    private static String[] errorBriefs = {"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)", "at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)"};        //错误摘要
    private static String[] errorDetails = {"java.lang.NullPointerException\\n    " + "at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\n " + "at cn.lift.dfdf.web.AbstractBaseController.validInbound", "at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67)\\n " + "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\n" + " at java.lang.reflect.Method.invoke(Method.java:606)\\n"};        //错误详情
    //页面id
    private static String[] pageIds = {"list.html", "main.html", "test.html"};
    //访问顺序号，0为第一个页面
    private static int[] visitIndexs = {0, 1, 2, 3, 4};
    //下一个访问页面，如为空则表示为退出应用的页面
    private static String[] nextPages = {"list.html", "main.html", "test.html", null};
    //当前页面停留时长
    private static Long[] stayDurationSecsS = {new Long(45), new Long(2), new Long(78)};

    //启动相关信息的数组
    private static AppStartupLog[] appStartupLogs = initAppStartupLogs();
    //页面跳转相关信息的数组
    private static AppPageLog[] appPageLogs = initAppPageLogs();
    //事件相关信息的数组
    private static AppEventLog[] appEventLogs = initAppEventLogs();
    //app使用情况相关信息的数组
    private static AppUsageLog[] appUsageLogs = initAppUsageLogs();
    //错误相关信息的数组
    private static AppErrorLog[] appErrorLogs = initAppErrorLogs();

    private static String[] initDeviceId() {
        String base = "device22";
        String[] result = new String[100];
        for (int i = 0; i < 100; i++) {
            result[i] = base + i + "";
        }
        return result;
    }

    private static Long[] initSingleUseDurationSecs() {
        Random random = new Random();
        Long[] result = new Long[200];
        for (int i = 1; i < 200; i++) {
            result[i] = (long) random.nextInt(200);
        }
        return result;
    }

    //启动相关信息的数组
    private static AppStartupLog[] initAppStartupLogs() {
        AppStartupLog[] result = new AppStartupLog[10];
        for (int i = 0; i < 10; i++) {
            AppStartupLog appStartupLog = new AppStartupLog();
            appStartupLog.setCountry(countrys[random.nextInt(countrys.length)]);
            appStartupLog.setProvince(provinces[random.nextInt(provinces.length)]);
            appStartupLog.setNetwork(networks[random.nextInt(networks.length)]);
            appStartupLog.setCarrier(carriers[random.nextInt(carriers.length)]);
            appStartupLog.setDeviceStyle(deviceStyles[random.nextInt(deviceStyles.length)]);
            appStartupLog.setScreenSize(screenSizes[random.nextInt(screenSizes.length)]);
            appStartupLog.setOsType(osTypes[random.nextInt(osTypes.length)]);
            appStartupLog.setBrand(brands[random.nextInt(brands.length)]);

            result[i] = appStartupLog;
        }
        return result;
    }

    //页面跳转相关信息的数组
    private static AppPageLog[] initAppPageLogs() {
        AppPageLog[] result = new AppPageLog[10];
        for (int i = 0; i < 10; i++) {
            AppPageLog appPageLog = new AppPageLog();
            String pageId = pageIds[random.nextInt(pageIds.length)];
            int visitIndex = visitIndexs[random.nextInt(visitIndexs.length)];
            String nextPage = nextPages[random.nextInt(nextPages.length)];
            while (pageId.equals(nextPage)) {
                nextPage = nextPages[random.nextInt(nextPages.length)];
            }
            Long stayDurationSecs = stayDurationSecsS[random.nextInt(stayDurationSecsS.length)];

            appPageLog.setPageId(pageId);
            appPageLog.setStayDurationSecs(stayDurationSecs);
            appPageLog.setVisitIndex(visitIndex);
            appPageLog.setNextPage(nextPage);

            result[i] = appPageLog;
        }
        return result;
    }

    ;

    //事件相关信息的数组
    private static AppEventLog[] initAppEventLogs() {
        AppEventLog[] result = new AppEventLog[10];
        for (int i = 0; i < 10; i++) {
            AppEventLog appEventLog = new AppEventLog();
            appEventLog.setEventId(eventIds[random.nextInt(eventIds.length)]);
            appEventLog.setParamKeyValueMap(paramKeyValueMapsS[random.nextInt(paramKeyValueMapsS.length)]);
            appEventLog.setEventDurationSecs(eventDurationSecsS[random.nextInt(eventDurationSecsS.length)]);

            result[i] = appEventLog;
        }
        return result;
    }

    ;

    //app使用情况相关信息的数组
    private static AppUsageLog[] initAppUsageLogs() {
        AppUsageLog[] result = new AppUsageLog[10];
        for (int i = 0; i < 10; i++) {
            AppUsageLog appUsageLog = new AppUsageLog();
            appUsageLog.setSingleUseDurationSecs(singleUseDurationSecsS[random.nextInt(singleUseDurationSecsS.length)]);

            result[i] = appUsageLog;
        }
        return result;
    }

    ;

    //错误相关信息的数组
    private static AppErrorLog[] initAppErrorLogs() {
        AppErrorLog[] result = new AppErrorLog[10];
        for (int i = 0; i < 10; i++) {
            AppErrorLog appErrorLog = new AppErrorLog();
            appErrorLog.setErrorBrief(errorBriefs[random.nextInt(errorBriefs.length)]);
            appErrorLog.setErrorDetail(errorDetails[random.nextInt(errorDetails.length)]);

            appErrorLog.setOsType(osTypes[random.nextInt(osTypes.length)]);
            appErrorLog.setDeviceStyle(deviceStyles[random.nextInt(deviceStyles.length)]);
            result[i] = appErrorLog;
        }
        return result;
    }


    public static void main(String[] args) {
        dataBuilder();
    }

    private static void dataBuilder() {
        Random random = new Random();
        try {
            //发送数据
            for (int i = 1; i <= 200000000; i++) {
                AppLogEntity logEntity = new AppLogEntity();
                //渠道
                logEntity.setAppChannel(appChannels[random.nextInt(appChannels.length)]);
                //appid
                logEntity.setAppId(appId);
                //platform
                logEntity.setAppPlatform(appPlatforms[random.nextInt(appPlatforms.length)]);
                logEntity.setAppVersion(appVersions[random.nextInt(appVersions.length)]);
                logEntity.setTenantId(tenantIds[random.nextInt(tenantIds.length)]);
                logEntity.setDeviceId(deviceIds[random.nextInt(deviceIds.length)]);
                logEntity.setOsType(osTypes[random.nextInt(osTypes.length)]);
                logEntity.setDeviceStyle(deviceStyles[random.nextInt(deviceStyles.length)]);

                // log集合
                logEntity.setAppStartupLogs(new AppStartupLog[]{appStartupLogs[random.nextInt(appStartupLogs.length)]});
                logEntity.setAppEventLogs(new AppEventLog[]{appEventLogs[random.nextInt(appEventLogs.length)]});
                logEntity.setAppErrorLogs(new AppErrorLog[]{appErrorLogs[random.nextInt(appErrorLogs.length)]});
                logEntity.setAppPageLogs(new AppPageLog[]{appPageLogs[random.nextInt(appPageLogs.length)]});
                logEntity.setAppUsageLogs(new AppUsageLog[]{appUsageLogs[random.nextInt(appUsageLogs.length)]});
                try {
                    //日志创建时间的设置
                    for (AppBaseLog log : logEntity.getAppStartupLogs()) {
                        log.setCreatedAtMs(System.currentTimeMillis());
                    }
                    for (AppBaseLog log : logEntity.getAppErrorLogs()) {
                        log.setCreatedAtMs(System.currentTimeMillis());
                    }
                    for (AppBaseLog log : logEntity.getAppEventLogs()) {
                        log.setCreatedAtMs(System.currentTimeMillis());
                    }
                    for (AppBaseLog log : logEntity.getAppPageLogs()) {
                        log.setCreatedAtMs(System.currentTimeMillis());
                    }
                    for (AppBaseLog log : logEntity.getAppUsageLogs()) {
                        log.setCreatedAtMs(System.currentTimeMillis());
                    }

                    //将对象转换成json string
                    String json = JSONObject.toJSONString(logEntity);
                    UploadUtil.upload(json);
                    System.out.println(222222);
                    System.out.println(json);
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
