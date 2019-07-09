package com.yujunquan.studylife.test;

public class OtherTest {
    public static void main(String[] args) {

        OtherTest otherTest = new OtherTest();
        double d = 11d;
        double v = otherTest.doubleTest(d);
        System.out.println(v);

    }


    Double doubleTest(Double d){
        return  d;
    }
    double doubleTest(double d){
        return d;
    }
}
