package com.yujunquan.studylife.test;

import java.util.HashMap;
import java.util.Map;

public class IntergerTest {
    public static void main(String[] args) {
        int index = 0;
        HashMap<Integer, String> map = new HashMap<>();
        map.put(index, "s1");
        ++index;
        map.put(index, "s2");
        ++index;
        map.put(index, "s3");
        ++index;
        map.put(index, "s4");
        ++index;
        map.put(index, "s5");
        ++index;
        map.put(index, "s6");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
        }
    }

    public static void main2(String[] args) {
        int i = (int) (2 / 3 + 1);
        System.out.println(i);
    }
}
