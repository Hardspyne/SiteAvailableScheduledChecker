package com.zubarev.websiteavailabilitychecker.scheduler;

import com.zubarev.websiteavailabilitychecker.model.SiteCheckScheduledTask;
import com.zubarev.websiteavailabilitychecker.utils.HttpUrlConnectionUtil;
import com.zubarev.websiteavailabilitychecker.utils.MailSenderUtil;
import com.zubarev.websiteavailabilitychecker.utils.ScreenshotSiteTakerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
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

                    if (statusCode != currentStatusCode) {
                        ScreenshotSiteTakerUtil.goToSite(task.getSite());
                        ScreenshotSiteTakerUtil.takeScreenshot();

                        mailSenderUtil.sendMessageWithAttachment(
                                task.getEmail(),task.getSite(),currentStatusCode,"screenshots/screenshot.png");
                        break;
                    }
                } catch (InterruptedException | MessagingException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return statusCode;
    }
}