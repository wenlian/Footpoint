package com.lia.animation.model;

public class StepsInfo {
    private String token = null;
    private String username = null;
    private String appname = "com.lia.animation";
    private String sessionid = null;
    private StepsDayDetail[] detaillist = null;

    public StepsInfo () {
    }

    public StepsInfo (String userName,String token,String sessionId,StepsDayDetail[] detaiList) {
        this.username = userName;
        this.token = token;
        this.sessionid = sessionId;
        this.detaillist = detaiList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionId() {
        return username;
    }

    public void setSessionId(String sessionId) {
        this.sessionid = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public StepsDayDetail[] getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(StepsDayDetail[] detaillist) {
        this.detaillist = detaillist;
    }

}