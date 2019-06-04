package com.yujunquan.studylife.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("test1");
        strings.add("test2");
        strings.add("test3");
        strings.add("test4");

        String cycle = new ListTest().cycle(strings);
        System.out.println(cycle);

    }

    private String cycle(List<String> strings ){
        for (String string : strings) {
            System.out.println("before:"+string);
            if("test2".equals(string)){
                return string;
            }
            System.out.println("after:"+string);
        }
        return "out";
    }



}
