package com.yujunquan.studylife.test;

import com.yujunquan.studylife.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    /**
     * 这里是注释文档
     *
     * @param args
     */
    public static void main(String[] args) {
        User user = new User();
        Integer exchang = user.getUserID();
        exchang = 1;
        exchang = 1;
        exchang = 2;
        String string = new String();


        System.out.println("args = [" + args + "]");
        System.out.println("args = [" + args + "]");
        System.out.println("ListTest.main");
        System.out.println("args = [" + args + "]");
    }

    public static void main2(String[] args) {

        List<String> strings = new ArrayList<String>();
        strings.add("test1");
        strings.add("test2");
        strings.add("test3");
        strings.add("test4");
        for (String string : strings) {

        }
        if (true) {
        }


        for (int i = 0; i < strings.size(); i++) {

        }

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
