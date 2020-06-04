package com.yujunquan.studylife.testall.innerclass;

public class Test {
    public static void main(String[] args) {
        InnerClassTest innerClassTest = new InnerClassTest();
        innerClassTest.print1("class1打印内容");
        Class2 class2 = new Class2();
        class2.print2("class2打印的内容");
    }
}
