package com.yujunquan.studylife.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTest {


    public void test1(){

        System.out.println("JUnit test");
    }

    public static void mai4n(String[] args) {
        Date date = new Date(null);// 解析异常
        System.out.println(date);
    }

    public static void main(String[] args) {
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
