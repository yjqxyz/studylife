package com.yujunquan.studylife.test;

public class HashTest {
    public static void main(String[] args) {
        String str = "yujunquan";
        System.out.println(str.hashCode());
        Object obj = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        System.out.println(obj.equals(obj2));
        System.out.println(obj.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj3.hashCode());
    }
}
