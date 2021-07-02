package com.yjq.common.util;


/**
 * @author yujunquan
 * @Description 数字工具类
 * @createTime 2020-06-18 11:58:05
 */
public final class NumUtils {
    private NumUtils() {}

    /**
     * 比较期望值和实际值
     * @param expectation 期望值
     * @param reality     实际值
     * @return
     */
    public static boolean eq(Integer expectation, Integer reality){
        return expectation.equals(reality);
    }


    /**
     * 判断字符串 是否 int 类型
     * @param source
     * @return
     */
    public static boolean isInt(String src){

        try {
            Integer.parseInt(src);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
