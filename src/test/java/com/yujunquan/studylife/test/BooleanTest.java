package com.yujunquan.studylife.test;

public class BooleanTest {
    private Boolean flag;

    public static void main(String[] args) {
        /*boolean b1 = m1();
        boolean b2 = m2();*/
        boolean b3 = m1() && m2();
        System.out.println(b3);
    }

    private static boolean m1() {
        System.out.println("I'm m1 method");
        return true;
    }


    private static boolean m2() {
        System.out.println("I'm m2 method");
        return false;
    }

    public static void main1(String[] args) {

        Boolean flag = new Boolean(true);
        flag.equals("1");

        boolean equals = "1".equals(flag);
    }
}
