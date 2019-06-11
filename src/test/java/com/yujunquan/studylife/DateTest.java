package com.yujunquan.studylife;

import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        Date date = new Date();

        Date date1 = new Date();
        System.out.println(date.getTime());
        System.out.println(date1.getTime());
        if(date.equals(date1)){
            System.out.println(date.getTime());
            System.out.println(date1.getTime());
            System.out.println("相等");

        }
        System.out.println("test");
    }
}
