package com.hyjj.hyjjservice.controller.report.viewobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel
public class AuditVO {

    @ApiModelProperty(value = "类型，0为企事业监测点报表，1为海洋统计年报",example = "1")
    @NotBlank(message = "类型不能为空")
    Integer type;

    @ApiModelProperty(value = "年份，传回一个年份的字符串",example = "2020")
    @NotBlank(message = "年份不能为空，且只传一个")
    String year;

    @ApiModelProperty(value = "行业，按照企业名录之前那个接口来，从左到右如果打钩就是1否则就是0，传一个长度为15的只包含0和1字符串给我。",example = "000001100000000")
    @NotBlank(message = "行业不能为空")
    String industry;

    @ApiModelProperty(value = "审核状态，0为全部，1未审核，2通过",example = "1")
    @NotBlank(message = "审核状态不能为空")
    Integer status;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
