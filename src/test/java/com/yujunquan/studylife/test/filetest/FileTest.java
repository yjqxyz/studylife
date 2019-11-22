package com.yujunquan.studylife.test.filetest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileTest {
    public static void main(String[] args) {

        try {
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".jpg");
            System.out.println(tempFile.getPath());
            System.out.println(tempFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
