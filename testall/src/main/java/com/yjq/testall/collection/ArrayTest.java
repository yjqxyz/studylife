package com.yjq.testall.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args) {
        String[] array = {"a", "b", "c"};
        List<String> resultList = new ArrayList<String>(Arrays.asList(array));
    }
}
