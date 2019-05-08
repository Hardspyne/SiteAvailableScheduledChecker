package com.zubarev.websiteavailabilitychecker.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.UnknownHostException;


public class HttpUrlConnectionUtil {

    public static int getStatusCode(String site) throws IOException {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpGet httpGet = new HttpGet(site);

            RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
            requestConfigBuilder.setConnectionRequestTimeout(10000).setMaxRedirects(1);
            httpGet.setConfig(requestConfigBuilder.build());

            HttpResponse resp = httpClient.execute(httpGet);

            return resp.getStatusLine().getStatusCode();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // if server unreachable send 0 status code
        return 0;
    }

}
