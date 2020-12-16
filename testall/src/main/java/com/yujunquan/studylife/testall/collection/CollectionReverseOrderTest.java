package com.yujunquan.studylife.testall.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yujunquan
 * @Description 描述
 * @createTime 2020-06-15 19:13:19
 */
public class CollectionReverseOrderTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            list.add(String.valueOf(i));
        }
        list.stream().forEach(System.out::print);
        System.out.println("\r\n===============================");
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);
        System.out.println("\r\n===============================");
        String collect = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.joining(" - "));
        System.out.println(collect);
    }
}
