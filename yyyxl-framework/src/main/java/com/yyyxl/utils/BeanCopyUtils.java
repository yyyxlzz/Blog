package com.yyyxl.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    /**
     * 单个实体类拷贝
     * @return
     */
    public static <V> V copyBean(Object source,Class<V> clazz) {
        // 创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            // 实现属性拷贝
            BeanUtils.copyProperties(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 返回结果
        return result;
    }

    /**
     * 集合
     * @param list
     * @param clazz
     * @param <O>
     * @param <V>
     * @return
     */
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
