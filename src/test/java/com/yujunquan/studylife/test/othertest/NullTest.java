package com.yujunquan.studylife.test.othertest;

import com.yujunquan.studylife.entity.User;

public class NullTest {
    public static void main(String[] args) {
        String user = null;
        if (user != null) {
            System.out.println("user != null");
        } else {
            System.out.println("user == null");

        }
    }

    public static void main2(String[] args) {
        User user = new User();
        System.out.println(user);
        if (user.getUserID() != null) {
            System.out.println("userId != null");
        } else {
            System.out.println("userId == null");
        }
        if (null == user.getUserInfo()) {
            System.out.println("userInfo  == null");
        }
        System.out.println(user.getUserInfo());
    }
}
