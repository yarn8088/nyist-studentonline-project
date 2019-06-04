package com.nyist.studentonline.logs.common;

/**
 * @Author: skm
 * @Date: 2019/5/24 10:41
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */
public class GeoInfo {
    private String country;
    private String province;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
