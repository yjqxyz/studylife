package com.yujunquan.studylife.test;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UUID uuid2 = UUID.randomUUID();
        String str = uuid2.toString();
        System.out.println(str);
        String strNew = str.replaceAll("-", "");
        System.out.println(strNew);
        System.out.println(strNew.length());
        //b72476b3-4b14-421b-a155-a32e534ccf1a
        //5501c3f4-a595-4057-9845-5eb94411c57f
    }
}
