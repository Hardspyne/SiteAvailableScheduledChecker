package com.zubarev.websiteavailabilitychecker.controller;

import com.zubarev.websiteavailabilitychecker.model.WebSiteCheckScheduledTask;
import com.zubarev.websiteavailabilitychecker.scheduler.WebSiteCheckTaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class WebsiteCheckerController {

    @Autowired
    WebSiteCheckTaskScheduler webSiteCheckTaskScheduler;

    @GetMapping("/")
    public String createScheduledTask(Model model) {
        WebSiteCheckScheduledTask webSiteCheckScheduledTask = new WebSiteCheckScheduledTask();
        model.addAttribute("task", webSiteCheckScheduledTask);
        return "task-schedule";
    }

    @PostMapping("/start-task")
    public String startScheduledTask(@ModelAttribute("task") WebSiteCheckScheduledTask webSiteCheckScheduledTask, Model model) throws IOException {
        model.addAttribute("status", webSiteCheckTaskScheduler.checkSiteStatus());
        return "result";
    }


}
