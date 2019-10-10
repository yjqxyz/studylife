package com.yujunquan.studylife.test;

public class CharTest {
    public static void main(String[] args) {
        char c = '\u6666';
        char c2 = '\u8208';
        System.out.println(c);
        System.out.println(c2);
    }

    public static void main3(String[] args) {
        char ch1 = 0;
        int i = 0;
        while (i < 65535) {
            i++;
            ch1++;
            System.out.println(" " + i + "=" + ch1);
        }
        String str = "离";
        System.out.println();

    }

    public static void main2(String[] args) {
        char ch1 = 9;
    }
}
