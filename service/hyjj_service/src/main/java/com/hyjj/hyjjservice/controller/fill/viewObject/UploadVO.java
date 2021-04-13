package com.hyjj.hyjjservice.controller.fill.viewObject;

import org.springframework.web.multipart.MultipartFile;

public class UploadVO {
    private MultipartFile file;
    private String reportId;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
}
