package com.yjq.testall.string;

/**
 * @author yujunquan
 * @Description 描述
 * @createTime 2020-06-09 16:05:23
 */
public class StringTest {
    public static void main(String[] args) {
        subStringTest();
    }

    /**
     * \r\n 截取测试
     */
    static void subStringTest(){
        String str = "1234\r\n";
        System.out.println(str);// 换行
        System.out.println(str.length());// 6
        String substring = str.substring(0, str.length() - 1);
        System.out.println(substring);// 不换行
        System.out.println(substring.length());// 5
        System.out.println("\r\n".length());// 2
        System.out.println("\\r\\n");// \r\n
        System.out.println("\\r\\n".length());// 4
    }
}
