package com.yujunquan.studylife.test;

import com.yujunquan.studylife.entity.User;

import java.util.HashMap;
import java.util.Map;

public class IntergerTest {
    public static void main(String[] args) {
        int a = 0;
        for (int i = 0; i < 99; i++) {
            a = ++a;
        }
        System.out.println(a);
    }

    public static void main4(String[] args) {
        int i = 1;
        User user = new User();
        User user2 = new User();
        user.setUserID(++i);
        System.out.println(user.getUserID());
        user2.setUserID(i++);
        System.out.println(user2.getUserID());
    }

    public static void main3(String[] args) {
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
