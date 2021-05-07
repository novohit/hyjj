package com.hyjj.hyjjservice.controller.fill.dto;

import java.util.List;

public class FormulaVerificationDto {
    private Long reportId;
    private List<Long> data;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }
}
