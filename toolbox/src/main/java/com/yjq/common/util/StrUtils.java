package com.yjq.common.util;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yujunquan
 * @Description 字符串工具类
 * @createTime 2020-06-15 17:28:15
 */
public class StrUtils{

    /**
     * 字符串真假
     *
     * @param flag
     * @return
     */
    public static boolean isTrue(String flag) {
        return CommonConsts.Str.TRUE.equals(flag);
    }

    public static boolean isFalse(String flag) {
        return CommonConsts.Str.FALSE.equals(flag);
    }

    /**
     * 转换字符串真假
     *
     * @param flag
     * @return
     */
    public static String switchTrueFalse(String flag) {
        if (isTrue(flag)) {
            return CommonConsts.Str.FALSE;
        } else if (isFalse(flag)) {
            return CommonConsts.Str.TRUE;
        }
        return "";
    }

    /**
     * 特殊字符添加不解析标记，使用字符数据
     *
     * @param src
     * @return
     */
    public static String specialCharsCDATA(String src) {
        return "<![CDATA[" + src + "]]>";
    }

    /**
     * null -> ""
     *
     * @param src
     * @return
     */
    public static String null2Empty(String src) {
        return src == null ? "" : src;
    }


    /**
     * null or empty -> "0"
     * @param source
     * @return
     */
    public static String nullOrEmpty2Zero(String source){
        if(StringUtils.isEmpty(source)){
            return CommonConsts.Str.ZERO;
        }else {
            return source;
        }

    }

    /**
     * 两个字符串不等
     *
     * @param cs1
     * @param cs2
     * @return
     */
    public static boolean notEquals(final CharSequence cs1, final CharSequence cs2) {
        return !StringUtils.equals(cs1, cs2);
    }

    /**
     * 使用符号连接字符串
     *
     * @param symbol
     * @param strs
     * @return
     */
    public static String concatWithSymbol(String symbol, String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str).append(symbol);
        }
        String tmp = sb.toString();
        // 符号开头的干掉
        if (tmp.startsWith(symbol)) {
            tmp = tmp.substring(1);
        }
        // 截除最后一个符号并返回
        return tmp.substring(0, tmp.length() - 1);
    }

    /**
     * 删除符号及字符串,并做前后修正
     *
     * @param symbol
     * @param strs
     * @return
     */
    public static String trimWithSymbol(String symbol, String source, String target) {
        if (ObjUtils.hasEmpty(symbol, source, target)) {
            return source;
        }
        // 修 ",1234" 尾
        String str = source.replace(symbol + target, CommonConsts.Str.EMPTY);
        // 修 "1234," 头
        str = str.replace(target + symbol, CommonConsts.Str.EMPTY);
        // 修 "1234"  中
        str.replace(target, CommonConsts.Str.EMPTY);

        return str;
    }

    public static boolean fileNameIllegal(String fileName) {
        if(StringUtils.isEmpty(fileName)){
            return false;
        }
        //文件名特殊字符校验
        String str = fileName.replace(".", "");
        String pattern = "[^%&',;=?$\\x22]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        boolean matches = m.matches();
        //文件后缀名校验
        int begin = fileName.lastIndexOf(".");
        if (begin > 0) {
            String type = fileName.substring(begin).toLowerCase();
            String[] IllegalDocuments = {".jsp", ".jspx", ".php", ".sh", ".bat", ".asp", ".aspx"};
            matches &= !Arrays.asList(IllegalDocuments).contains(type);
        }
        return !matches;
    }

    public static String trimStrKeepQuantity(String source,int keepQuantity){

        if(StringUtils.isEmpty(source)){
            return source;
        }
        if (source.length() > keepQuantity) {
            source = source.substring(0, keepQuantity);
        }

        return source;

    }


    public static String removeOneFromArrayStr(String strs, String deleteStr) {
        // 返回结果
        String result = "";
        // 判断是否存在。如果存在，移除指定用户 ID；如果不存在，则直接返回空
        if(strs.indexOf(",") != -1) {
            // 拆分成数组
            String[] userIdArray = strs.split(",");
            // 数组转集合
            List<String> userIdList = new ArrayList<String>(Arrays.asList(userIdArray));
            // 移除指定用户 ID
            String[] deleteStrs = deleteStr.split(",");
            for (int i = 0; i < deleteStrs.length; i++) {
                userIdList.remove(deleteStrs[i]);
            }
            // 把剩下的用户 ID 再拼接起来
            result = StringUtils.join(userIdList, ",");
        }
        // 返回
        return result;
    }
}
