package com.hyjj.hyjjservice.controller.report.viewobject;

public class AuditDto {
    Long reportId;
    Integer judge;
    String tailHtml;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Integer getJudge() {
        return judge;
    }

    public void setJudge(Integer judge) {
        this.judge = judge;
    }

    public String getTailHtml() {
        return tailHtml;
    }

    public void setTailHtml(String tailHtml) {
        this.tailHtml = tailHtml;
    }
}
