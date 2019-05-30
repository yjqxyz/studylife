package com.yujunquan.studylife.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HTTPTest1 {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    }
}
