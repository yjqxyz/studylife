package com.yujunquan.studylife.testall.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class LambdaTest2 {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("cuzz", "faker", "mlxg");

        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass());

    }

    static String test(String str) {
        return str + " test";
    }
}
