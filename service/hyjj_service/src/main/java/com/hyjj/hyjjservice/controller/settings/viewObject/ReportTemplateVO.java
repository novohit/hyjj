package com.hyjj.hyjjservice.controller.settings.viewObject;

import java.util.Date;

public class ReportTemplateVO {
    private String reportClass;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportClass() {
        return reportClass;
    }

    public void setReportClass(String reportClass) {
        this.reportClass = reportClass;
    }

    private Integer repClass;
    private String name;
    private String madeRepOrg;
    private String auditingOrg;
    private String auditingCode;
    private Date gmtCreate;
    private String title;
    private String timeFrom;
    private String timeTo;
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "ReportTemplateVO{" +
                "reportClass='" + reportClass + '\'' +
                ", repClass=" + repClass +
                ", name='" + name + '\'' +
                ", madeRepOrg='" + madeRepOrg + '\'' +
                ", auditingOrg='" + auditingOrg + '\'' +
                ", auditingCode='" + auditingCode + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", title='" + title + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", pageNum=" + pageNum +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getRepClass() {
        return repClass;
    }

    public void setRepClass(Integer repClass) {
        if(repClass == 0){
            this.setReportClass(this.getName()+"企事业年表");
        }
        if(repClass == 1){
            this.setReportClass(this.getName()+"企事业月表");
        }
        if(repClass == 3){
            this.setReportClass("单位基本情况表");
        }
        this.repClass = repClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
