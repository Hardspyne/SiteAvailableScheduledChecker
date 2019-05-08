package com.zubarev.websiteavailabilitychecker.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;


public class HttpUrlConnectionUtil {

    public static int getStatusCode(String site) throws IOException {
        try {
            URL siteURl = new URL(site);
            HttpURLConnection urlConnection = (HttpURLConnection) siteURl.openConnection();
            return urlConnection.getResponseCode();
            //catch timeout connection or unreachable host exception
        } catch (SocketException | UnknownHostException e ) {
            System.out.println(e);
        }
        // if server unreachable send 0 status code
        return 0;
    }

}
