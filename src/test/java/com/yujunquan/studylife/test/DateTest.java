package com.yujunquan.studylife.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTest {
    public static void main(String[] args) {
        SimpleDateFormat sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH时mm分");
        String dateStr = sdfTmp.format(new Date());
        System.out.println(dateStr);

    }

    public static void main8(String[] args) {
    System.out.println(new Date().getTime());
    System.out.println(System.currentTimeMillis());
    System.out.println(new Date(1562567906855L));
  }

    public void test1(){

        new Boolean("");
        System.out.println("JUnit test");
    }

    public static void main7(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sdf.parse("2019-11-11 24:9:55");//解析异常
            System.out.println(parse);
            System.out.println(sdf.format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main6(String[] args) {
        Date date = new Date(null);// 解析异常
        System.out.println(date);
    }

    public static void main5(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = "2019-01-01 0:11:00";
        try {
            Date parse = sdf.parse(strDate);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void main2(String[] args) {
        Date date = new Date();

        Date date1 = new Date();
        System.out.println(date.getTime());
        System.out.println(date1.getTime());
        if(date.equals(date1)){
            if(date.after(date1)){

            }
            System.out.println(date.getTime());
            System.out.println(date1.getTime());
            System.out.println("相等");

        }
        System.out.println("test");
    }
}
