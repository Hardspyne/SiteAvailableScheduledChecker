package com.zubarev.websiteavailabilitychecker.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionUtil {

    public static int getStatusCode(String site) throws IOException {
        URL url = new URL(site);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        return connection.getResponseCode();
    }
}
