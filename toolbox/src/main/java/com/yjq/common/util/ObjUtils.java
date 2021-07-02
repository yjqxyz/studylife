package com.yjq.common.util;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author yujunquan
 * @Description 对象工具类
 * @createTime 2020-06-09 15:53:27
 */
public final class ObjUtils{
    private ObjUtils() {}

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            return ((String) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).size() == 0;
        } else if (obj instanceof Map) {
            return ((Map) obj).size() == 0;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Boolean) {
            return false;
        } else if (obj instanceof Number) {
            return false;
        } else if (obj instanceof Character) {
            return false;
        } else if (obj instanceof Date) {
            return false;
        } else {
            return false;
        }
    }
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static <E> boolean isEmpty(Collection<E> c) {
        return c == null || c.isEmpty();
    }

    public static <K, E> boolean isEmpty(Map<K, E> m) {
        return m == null || m.isEmpty();
    }

    public static boolean isEmpty(CharSequence c) {
        return c == null || c.length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }

    public static <E> boolean isNotEmpty(Collection<E> c) {
        return c != null && !c.isEmpty();
    }

    public static boolean isNotEmpty(CharSequence c) {
        return c != null && c.length() > 0;
    }


    /**所有参数均不为空*/
    public static boolean isAllNotEmpty(Object... objs) {
        for (Object obj : objs) {
            boolean b = isNotEmpty(obj);
            if(!b){
                return false;
            }
        }
        return true;
    }
    /**有参数为空*/
    public static boolean hasEmpty(Object... objs) {
       return !isAllNotEmpty(objs);
    }




}
