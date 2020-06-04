package com.yujunquan.studylife.testall.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("-----------------");
        for (int val : list) {
            System.out.println(val);
        }

        System.out.println("-----------------");
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("-----------------");
        list.forEach(integer -> System.out.println(integer));
        System.out.println("-----------------");
        list.forEach(integer -> System.out.println(integer));
    }
}
