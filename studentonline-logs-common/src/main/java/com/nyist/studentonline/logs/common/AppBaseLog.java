package com.nyist.studentonline.logs.common;

import java.io.Serializable;

/**
 * AppBaseLog
 */
public class AppBaseLog implements Serializable {
    private Long createdAtMs;            //日志创建时间
    private String appId;                //应用唯一标识，也就是studentonlineAPP的id
    private String tenantId;             //学校唯一标识
    private String deviceId;            //设备唯一标识
    private String appVersion;            //版本
    private String appChannel;            //渠道,安装时就在清单中制定了，appStore等。
    private String appPlatform;            //平台
    private String osType;                //操作系统
    private String deviceStyle;            //机型

    public Long getCreatedAtMs() {
        return createdAtMs;
    }

    public void setCreatedAtMs(Long createdAtMs) {
        this.createdAtMs = createdAtMs;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getAppPlatform() {
        return appPlatform;
    }

    public void setAppPlatform(String appPlatform) {
        this.appPlatform = appPlatform;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getDeviceStyle() {
        return deviceStyle;
    }

    public void setDeviceStyle(String deviceStyle) {
        this.deviceStyle = deviceStyle;
    }
}
