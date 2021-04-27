package com.hyjj.hyjjservice.controller.settings.viewObject;

public class SearchReportTemplateVO {
    private String title;
    private String number;
    private String madeRepOrg;
    private String auditingOrg;
    private String auditingCode;
    private String timeFrom;
    private String timeTo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMadeRepOrg() {
        return madeRepOrg;
    }

    public void setMadeRepOrg(String madeRepOrg) {
        this.madeRepOrg = madeRepOrg;
    }

    public String getAuditingOrg() {
        return auditingOrg;
    }

    public void setAuditingOrg(String auditingOrg) {
        this.auditingOrg = auditingOrg;
    }

    public String getAuditingCode() {
        return auditingCode;
    }

    public void setAuditingCode(String auditingCode) {
        this.auditingCode = auditingCode;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
}
