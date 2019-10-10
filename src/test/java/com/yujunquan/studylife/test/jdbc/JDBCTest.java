package com.yujunquan.studylife.test.jdbc;

public class JDBCTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.jdbc.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
