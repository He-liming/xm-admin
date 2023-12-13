package com.xm.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;

/**
 * jpa 工具类
 *
 * @author xiaoming
 * @date 2023-12-13 22:45
 **/
public class JpaUtils {

    /**
     * 从 src 中复制不为 null 的字段到 target
     *
     * @param src    源实体
     * @param target 目标实体
     */
    public static void copyNotNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 获取实体中为null的属性名称
     *
     * @param source 实体类
     * @return 需要过滤掉的属性名称
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                // 获取每一个属性名称
                .map(FeatureDescriptor::getName)
                // 过滤掉属性值不为null的字段
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }

}
