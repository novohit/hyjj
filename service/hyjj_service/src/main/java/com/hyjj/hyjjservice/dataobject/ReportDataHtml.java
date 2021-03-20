package com.hyjj.hyjjservice.dataobject;

import io.swagger.models.auth.In;

public class ReportDataHtml {
    private String headHtml;
    private String bodyHtml;
    private String tailHtml;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
