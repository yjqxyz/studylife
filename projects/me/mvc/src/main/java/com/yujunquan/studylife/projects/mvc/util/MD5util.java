package com.yujunquan.studylife.projects.mvc.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

public class MD5util {
    public static void main(String[] args) {
        String s = DigestUtils.md5Hex("hello");
        MessageDigest digest = DigestUtils.getDigest("md5");
        String algorithm = digest.getAlgorithm();


        System.out.println(algorithm);

        System.out.println(s);

    }
}
