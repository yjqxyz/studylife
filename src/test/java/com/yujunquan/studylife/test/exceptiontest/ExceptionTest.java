package com.yujunquan.studylife.test.exceptiontest;

import com.yujunquan.studylife.entity.PrarentTest;

public class ExceptionTest extends PrarentTest {

    public static void main(String[] args) {

    }

    @Override
    public void print() throws Exception {
        super.print();
        throw new Exception("asdf");
    }
}
