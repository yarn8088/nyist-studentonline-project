package com.nyist.studentonline.logs.util;

/**
 * @Author: skm
 * @Date: 2019/5/20 20:55
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 使用内省的方式复制属性
 */
public class PropertiesUtil {
    public static void copyProperties(Object src, Object aim) {
        try {
            //获得源对象的bean信息
            BeanInfo bisrc = Introspector.getBeanInfo(src.getClass());
            //获得属性描述符
            PropertyDescriptor[] propertyDescriptors = bisrc.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                try {
                    //对象的getX方法
                    Method readMethod = propertyDescriptor.getReadMethod();
                    //对象的setX方法
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    //获得setX方法的方法名称
                    String name = writeMethod.getName();
                    //获得setX方法的参数类型
                    Class[] parameterTypes = writeMethod.getParameterTypes();


                    //获得src对象的get方法的返回值
                    Object value = readMethod.invoke(src);
                    //获得aim对象对应的方法
                    Method aimSetter = aim.getClass().getMethod(name, parameterTypes);
                    aimSetter.invoke(aim, value);

                } catch (Exception e) {
                    continue;
                }

            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 属性复制的方法
     */
    public static void copyProperties(Object src, Object[] aim) {
        for (Object aimO : aim) {
            copyProperties(src, aimO);
        }
    }
}
