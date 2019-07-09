package com.yujunquan.studylife.test;

public class StringTest {

    public static void main(String[] args) {
        String str1 = "str1";
        String str2 = "str2";
        String str3 = str1;
        System.out.println(str3.hashCode());
        str3 ="str3New";
        System.out.println(str3.hashCode());


    }

    public static void main4(String[] args) {
        String [] strArr = {"1","2"};
        System.out.println(strArr[1]);
    }

    public static void main3(String[] args) {
        String str =  "01234";
        System.out.println(str.length());
        String substring = str.substring(4, 6);
        System.out.println(substring);
    }


    public static void main2(String[] args) {
        String str = null;
        String str2 = "nihao :"+str+"!";
        System.out.println(str2);

        if(str != null){
            System.out.println("null == null");
        }


    }
}
