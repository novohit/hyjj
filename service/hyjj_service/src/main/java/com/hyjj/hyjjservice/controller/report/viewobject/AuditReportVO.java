package com.hyjj.hyjjservice.controller.report.viewobject;

import java.util.Date;

public class AuditReportVO {
    Long id;
    String fillPerson;
    String fillUnit;
    Long reportTemplateId;
    String title;
    String number;
    Date submitDate;
    String beginDate;
    String proStatus;
    String enterpriseName;
    Date reportDate;
    String name;

    String reportDateString;
    String submitDateString;

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

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
    }

    public Long getReportTemplateId() {
        return reportTemplateId;
    }

    public void setReportTemplateId(Long reportTemplateId) {
        this.reportTemplateId = reportTemplateId;
    }

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

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportDateString() {
        return reportDateString;
    }

    public void setReportDateString(String reportDateString) {
        this.reportDateString = reportDateString;
    }

    public String getSubmitDateString() {
        return submitDateString;
    }

    public void setSubmitDateString(String submitDateString) {
        this.submitDateString = submitDateString;
    }
}
