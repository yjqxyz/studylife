package com.yujunquan.studylife.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yujunquan.studylife.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        String jsonStr = "{\"k1\": \"v1\",'k2': 'v2'}";
        System.out.println(jsonStr);
        String s1 = JSON.toJSONString(jsonStr);
        System.out.println(s1);

        // 模拟 user began
        User user = new User();
        User user2 = new User();
        User user3 = new User();
        user.setUserID(1);
        user.setUserName("张三");
        user.setUserPwd("1234");
        user2.setUserID(1);
        user2.setUserName("李四四");
        user2.setUserPwd("1234");
        user3.setUserID(1);
        user3.setUserName("王五");
        user3.setUserPwd("1234");
        // 模拟 user end


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("k1", "v1");
        jsonObject.put("k2", "v2");
        jsonObject.put("k3", "v3");
        jsonObject.put("k4", "v4");
        jsonObject.put("user", user);
        String s = JSON.toJSONString(jsonObject);
        System.out.println(s);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(user);
        jsonArray.add(user2);
        jsonArray.add(user3);
        Object[] objects = jsonArray.toArray();

        List<Object> objects1 = new ArrayList<Object>(objects.length);
        boolean b = Collections.addAll(objects1, objects);
        if (b) {
            for (Object o : objects1) {
                User userTmp = (User) o;
                System.out.println(userTmp);
            }
        }
        List<Object> objs = new ArrayList<Object>(Arrays.asList(objects));
        for (Object obj : objs) {
            User userTmp = (User) obj;
            System.out.println(userTmp);
        }

        System.out.println(objects);
        String s2 = jsonArray.toJSONString();
        System.out.println(jsonArray);
        System.out.println(s2);


    }
}
