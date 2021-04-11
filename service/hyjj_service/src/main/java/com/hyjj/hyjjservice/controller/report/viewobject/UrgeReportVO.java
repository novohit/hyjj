package com.hyjj.hyjjservice.controller.report.viewobject;

import java.util.Date;

public class UrgeReportVO {
    Long id;
    String number;
    Date beginDate;
    Date endDate;
    String fillPerson;
    String beginDateToString;
    String endDateToString;
    String reportDateToString;

    public String getReportDateToString() {
        return reportDateToString;
    }

    public void setReportDateToString(String reportDateToString) {
        this.reportDateToString = reportDateToString;
    }

    public String getBeginDateToString() {
        return beginDateToString;
    }

    public void setBeginDateToString(String beginDateToString) {
        this.beginDateToString = beginDateToString;
    }

    public String getEndDateToString() {
        return endDateToString;
    }

    public void setEndDateToString(String endDateToString) {
        this.endDateToString = endDateToString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
