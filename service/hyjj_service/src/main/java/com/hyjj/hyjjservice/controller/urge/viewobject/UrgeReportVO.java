package com.hyjj.hyjjservice.controller.urge.viewobject;

import java.util.Date;

public class UrgeReportVO {
    Long id;
    String number;

    Date beginDate;
    Date endDate;

    String fillUnit;
    String fillPerson;

    String beginDateToString;
    String endDateToString;
    String reportDate;


    public String getFillUnit() {
        return fillUnit;
    }

    public void setFillUnit(String fillUnit) {
        this.fillUnit = fillUnit;
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

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }


}
