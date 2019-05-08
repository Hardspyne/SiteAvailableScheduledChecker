package com.zubarev.websiteavailabilitychecker.scheduler;

import com.zubarev.websiteavailabilitychecker.model.SiteCheckScheduledTask;
import com.zubarev.websiteavailabilitychecker.utils.HttpUrlConnectionUtil;
import com.zubarev.websiteavailabilitychecker.utils.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class SiteCheckTaskScheduler {

    @Autowired
    private MailSenderUtil mailSenderUtil;

    public int checkSiteStatus(SiteCheckScheduledTask task) throws IOException {
        int statusCode = HttpUrlConnectionUtil.getStatusCode(task.getSite());
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MINUTES.sleep(task.getRefreshTime());

                    int currentStatusCode = HttpUrlConnectionUtil.getStatusCode(task.getSite());

                    mailSenderUtil.sendMessage(task.getEmail(),task.getSite(),currentStatusCode);
                    if (statusCode != currentStatusCode) {
                        break;
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return statusCode;
    }
}
