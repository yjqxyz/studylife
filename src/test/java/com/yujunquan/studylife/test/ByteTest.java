package com.yujunquan.studylife.test;

public class ByteTest {
    public static void main(String[] args) {
        String str = "人";
        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {

            System.out.print("(" + aByte + ")");
        }
    }
}
