package com.yjq.common.util;

/**
 * @author yujunquan
 * @Description 通用常量类
 * @createTime 2020-08-14 15:28:15
 */
public final class CommonConsts {
    private CommonConsts() {}

    /**
     * 字符串
     */
    public static class Str {
        public static final String NULL = null;
        public static final String EMPTY = "";
        public static final String SPACE = " ";

        // 字符串代表的 boolean值  1:是 0:否  1:真  0:假
        public static final String TRUE = "1";
        public static final String FALSE = "0";
        // 字符串类型数字
        public static final String ZERO = "0";
        public static final String ONE = "1";
        public static final String TWO = "2";
        public static final String THREE = "3";
        public static final String FOUR = "4";
        public static final String FIVE = "5";
        public static final String TEN = "10";
    }



    /**
     * 符号
     */
    public static class Symbol {
        // 逗号
        public static final String COMMA = ",";
        // 点
        public static final String DOT = ".";
    }



    public static class DB {
        public static final String DEL_FLAG_0 = "0";
        public static final String DEL_FLAG_1 = "1";
    }



    public static class File {
        public static final String TYPE_VIDEO = "video";
        public static final String TYPE_AUDIO = "audio";
        public static final String TYPE_IMAGE = "image";
        public static final String TYPE_DOCUMENT = "document";
        public static final String TYPE_UNKNOWN = "unknown";
    }


}
