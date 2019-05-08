package com.zubarev.websiteavailabilitychecker.utils;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;


public class HttpUrlConnectionUtil {

    public static int getStatusCode(String site) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse resp = httpClient.execute(new HttpGet(site));
        return resp.getStatusLine().getStatusCode();
    }

    public static boolean isReachable(String site) throws IOException {
        InetAddress inetAddress =  InetAddress.getByName(new URL(site).getHost());
        return inetAddress.isReachable(10000);
    }
}
