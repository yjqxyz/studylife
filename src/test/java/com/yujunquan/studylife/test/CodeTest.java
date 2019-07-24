package com.yujunquan.studylife.test;

import java.io.UnsupportedEncodingException;

public class CodeTest {
    public static void main(String[] args) {
        byte b[] = {65, 97};
        String str = null;
        try {
            str = new String(b, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
