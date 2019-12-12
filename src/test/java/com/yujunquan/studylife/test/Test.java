package com.yujunquan.studylife.test;

import com.yujunquan.studylife.test.innerclasstest.InnerClassTest;

public class Test {
    public static void main(String[] args) {
        int v = Integer.parseInt("02");
        System.out.println(v);
    }
    public static void main4(String[] args) {
        double random = Math.random();
        System.out.println(random);
        System.out.println((int) (10 + random * 100));

    }

    public static void main3(String[] args) {
        System.out.println("\\");
        System.out.println("\'\"");
    }

    public static void main2(String[] args) {
        System.out.println("就是分辨率有点低了，最少也要买个1080p 的显示器才能完美的展示");
        System.out.println("这样真的可以很大提高效率");
        /**
         * 想办法提高效率，肯定是解决问题的最优办法
         * 通过时间的积累是最笨的方法
         * 但是没有更好的办法的时候，就是最好的办法
         *
         */
        InnerClassTest innerClassTest = new InnerClassTest();
        innerClassTest.print1("class1 打印");


    }
}
