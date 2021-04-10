package com.hyjj.hyjjservice.controller.fill.viewObject;

public class ReportDataList {
    private Long id;
    private String number;
    private String title;
    private String reportDate;
    private String fillUnit;
    private String submitDate;
    private String proStatusName;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getFillUnit() {
        return fillUnit;
    }

    public void setFillUnit(String fillUnit) {
        this.fillUnit = fillUnit;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getProStatusName() {
        return proStatusName;
    }

    public void setProStatusName(String proStatusName) {
        this.proStatusName = proStatusName;
    }
}
