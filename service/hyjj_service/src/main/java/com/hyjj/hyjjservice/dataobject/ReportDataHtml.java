package com.hyjj.hyjjservice.dataobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

@ApiModel
public class ReportDataHtml {

    @ApiModelProperty(value = "html的head，但这里的head并不是指<head>，而是指要填的报表以上的html")
    private String headHtml;

    @ApiModelProperty(value = "同上，html的body，指的是填报部分的html")
    private String bodyHtml;

    @ApiModelProperty(value = "同上，html的tail，指的是填报部分以下的html")
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
