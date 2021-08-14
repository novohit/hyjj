package com.hyjj.hyjjservice.controller.fill.dto;

import java.util.List;

public class FormulaVerificationDto {
    private Long reportId;
    private List<Double> data;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
