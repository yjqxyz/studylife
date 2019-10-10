package com.yujunquan.studylife.test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("str1");
        set.add("str2");
        set.add("str3");
        set.add(null);
        set.add(null);
        System.out.println(set.size());
        System.out.println(set.contains("str3"));
        System.out.println(set.contains("str4"));
        set.add("str4");
        System.out.println(set.size());
     /*   HashSet<Object> objects = new HashSet<>();
        objects.equals("");*/
    }
}
