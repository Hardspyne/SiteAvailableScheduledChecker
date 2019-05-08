package com.zubarev.websiteavailabilitychecker.scheduler;

import com.zubarev.websiteavailabilitychecker.model.SiteCheckScheduledTask;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class SiteCheckTaskScheduler {

    public int checkSiteStatus(SiteCheckScheduledTask task) throws IOException {
        URL url = new URL("http://example.com");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        return connection.getResponseCode();
    }
}
