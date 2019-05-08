package com.zubarev.websiteavailabilitychecker.model;

public class SiteCheckScheduledTask {
    private String site;
    private int refreshTime;
    private String email;

    public SiteCheckScheduledTask(String site, int refreshTime, String email) {

        this.site = site;
        this.refreshTime = refreshTime;
        this.email = email;
    }

    public SiteCheckScheduledTask() {
    }


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(int refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
