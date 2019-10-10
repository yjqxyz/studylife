package com.yujunquan.studylife.test;

public class ifTest {
    public static void main(String[] args) {
        int s1 = 0, s2 = 0, s3 = 0, temp = 0;
        if (s1 >= temp) {
            s1 -= temp;
        } else if ((s1 + s2) >= temp) {
            s1 = 0;
            s2 -= (temp - s1);
        } else {
            s1 = 0;
            s2 = 0;
            s3 -= (s1 + s2 - temp);
        }


        if (s1 >= temp) {
            s1 -= temp;
        } else {
            if (s1 + s2 > temp) {
                s1 = 0;
                s2 -= (temp - s1);
            } else {
                s1 = 0;
                s2 = 0;
                s3 -= (s1 + s2 - temp);
            }
        }
    }
}
