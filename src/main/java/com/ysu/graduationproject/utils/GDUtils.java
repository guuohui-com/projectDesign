package com.ysu.graduationproject.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class GDUtils {
    /**
     * 判空方法
     *
     * @name 中文名称
     * @description 相关说明
     * @param obj
     *            对象
     * @return true、false
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNull(Object obj) {
        boolean isNullFlag = true;
        if (obj != null) {
            if (obj instanceof List<?>) {
                isNullFlag = isNull((List<?>) obj);
            } else if (obj instanceof Set<?>) {
                isNullFlag = isNull((Set<?>) obj);
            } else if (obj instanceof Object[]) {
                isNullFlag = isNull((Object[]) obj);
            } else if (obj instanceof Map) {
                isNullFlag = isNull((Map) obj);
            } else if (obj instanceof String) {
                isNullFlag = isNull((String) obj);
            } else if (obj instanceof Integer) {
                isNullFlag = isNull((Integer) obj);
            } else {
                isNullFlag = false;
            }
        }
        return isNullFlag;
    }

    /**
     * 判断列表是否为空
     *
     * @name 中文名称
     * @description 相关说明
     * @time 创建时间:2017年2月14日下午1:06:44
     * @param list
     *            list
     * @return true、false;
     * @author 史雪涛
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static boolean isNull(List<?> list) {
        return list == null || list.size() == 0 || list.get(0) == null;
    }

    /**
     * 判断列表是否为空
     *
     * @name 中文名称
     * @description 相关说明
     * @param set
     *            set集合
     * @return true、false;
     */
    public static boolean isNull(Set<?> set) {
        return set == null || set.size() == 0;
    }

    /**
     * @name 判断数组是否为空
     * @description 相关说明
     * @param objects
     *            数组
     * @return true、false;
     */
    public static boolean isNull(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     *
     * @name 判断Map是否为空
     * @description 判断Map是否为空
     *            map集合
     * @return boolean值
   */
    public static boolean isNull(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim()) || "undefined".equalsIgnoreCase(str.trim());
    }

    /**
     * 判断整数是否为空
     * @param integer
     * @return
     */
    public static boolean isNull(Integer integer) {
        return integer == null;
    }

}
