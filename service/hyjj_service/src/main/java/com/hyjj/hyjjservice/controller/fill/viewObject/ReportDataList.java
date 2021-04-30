package com.hyjj.hyjjservice.controller.fill.viewObject;

public class ReportDataList {
    private Long id;
    private String number;
    private String title;
    private String endDate;
    private String fillUnit;
    private String beginDate;
    private String proStatusName;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getFillUnit() {
        return fillUnit;
    }

    public void setFillUnit(String fillUnit) {
        this.fillUnit = fillUnit;
    }



    public String getProStatusName() {
        return proStatusName;
    }

    public void setProStatusName(String proStatusName) {
        this.proStatusName = proStatusName;
    }
}
