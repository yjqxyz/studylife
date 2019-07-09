package com.yujunquan.studylife.test;

import java.math.BigDecimal;

public class NumberTest {
    public static void main(String[] args) {
        double d = 0.11;
        System.out.println(d);
        BigDecimal bigDecimal = new BigDecimal(d);
        System.out.println(bigDecimal);
        System.out.println(d);
    }
}
