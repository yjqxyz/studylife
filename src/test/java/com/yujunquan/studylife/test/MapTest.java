package com.yujunquan.studylife.test;

import com.yujunquan.studylife.entity.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.entrySet();
        map.put("code1","name1");
        map.put("code2","name2");
        System.out.println(map);
        HashMap hashMap = (HashMap)map;

        HashSet<Object> objects = new HashSet<Object>();
        User user1 = new User();

        System.out.println(user1.hashCode());
        User user11 =user1;
        User user2 = new User();
        System.out.println(user2.hashCode());
        objects.add(user1);
        //objects.add(user11);
        objects.add(user2);
        objects.add(null);
        System.out.println(objects.size());
        System.out.println(objects);

    }
}
