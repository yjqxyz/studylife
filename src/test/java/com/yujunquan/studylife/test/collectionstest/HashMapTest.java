package com.yujunquan.studylife.test.collectionstest;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    public static void main(String[] args) {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> map.put(UUID.randomUUID().toString(), "")).start();
            System.out.println(map.size());
        }
        System.out.println(map.size());
        for (String str : map.keySet()) {
            System.out.println(str);
        }

        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();

    }
}
