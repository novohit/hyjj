package com.hyjj.hyjjservice.controller.settings.viewObject;

import java.util.Date;
import java.util.List;

public class ReportTemplateInfoVO {
    private String name;
    private Integer repClass;
    private String number;
    private String madeRepOrg;
    private String auditingOrg;
    private String auditingCode;
    private Date expireDate;
    private List<String> comName;
    private String headHtml;
    private String bodyHtml;
    private String tailHtml;
    private String remark;
    private String reportClass;

    public String getReportClass() {
        return reportClass;
    }

    public void setReportClass(String reportClass) {
        this.reportClass = reportClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public List<String> getComName() {
        return comName;
    }

    public void setComName(List<String> comName) {
        this.comName = comName;
    }

    public String getHeadHtml() {
        return headHtml;
    }

    public void setHeadHtml(String headHtml) {
        this.headHtml = headHtml;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public String getTailHtml() {
        return tailHtml;
    }

    public void setTailHtml(String tailHtml) {
        this.tailHtml = tailHtml;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
