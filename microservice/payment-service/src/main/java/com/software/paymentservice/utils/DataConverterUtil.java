package com.software.paymentservice.utils;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据转换工具类
 */
public final class DataConverterUtil {
    /**
     * 将实体类转换为Map
     * @param bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Map<String, Object> convertBeanToMap(Object bean) throws IntrospectionException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();

            if (!"class".equals(propertyName)) {
                Method readMethod = propertyDescriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (null != result) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }

        return returnMap;
    }

    /**
     * 将Map转换为实体类
     * @param type
     * @param map
     * @return
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object convertMapToBean(Class<?> type, Map<String, Object> map) throws IntrospectionException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Object obj = type.newInstance();

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();

            if (map.containsKey(propertyName)) {
                try {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[]{value};
                    propertyDescriptor.getWriteMethod().invoke(obj, args);
                } catch (Exception e) {
                    System.err.println("The failure of " + propertyName + " assignment");
                }
            }
        }

        return obj;
    }
}
