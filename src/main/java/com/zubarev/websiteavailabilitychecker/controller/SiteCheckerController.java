package com.zubarev.websiteavailabilitychecker.controller;

import com.zubarev.websiteavailabilitychecker.model.SiteCheckScheduledTask;
import com.zubarev.websiteavailabilitychecker.scheduler.SiteCheckTaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class SiteCheckerController {

    @Autowired
    SiteCheckTaskScheduler siteCheckTaskScheduler;

    @GetMapping("/")
    public String createScheduledTask(Model model) {
        SiteCheckScheduledTask siteCheckScheduledTask = new SiteCheckScheduledTask();
        model.addAttribute("task", siteCheckScheduledTask);
        return "task-schedule";
    }

    @PostMapping("/start-task")
    public String startScheduledTask(@ModelAttribute("task") SiteCheckScheduledTask task, Model model) throws IOException {
        int siteStatus = siteCheckTaskScheduler.checkSiteStatus(task);

        if (siteStatus != 0) {
            model.addAttribute("status", siteStatus);
            return "result";
        } else {
            return "unreachable-host";
        }
    }
}
