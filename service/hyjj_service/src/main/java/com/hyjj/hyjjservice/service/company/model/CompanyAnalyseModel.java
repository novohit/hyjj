package com.hyjj.hyjjservice.service.company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;


@ApiModel("企业分析对象信息")
public class CompanyAnalyseModel implements Serializable {


    @ApiModelProperty("名字，例如地区，行业类型")
    private String dataName;

    @ApiModelProperty("数量")
    private Long dataNum;

    @ApiModelProperty("百分比")
    private BigDecimal percent;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Long getDataNum() {
        return dataNum;
    }

    public void setDataNum(Long dataNum) {
        this.dataNum = dataNum;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
