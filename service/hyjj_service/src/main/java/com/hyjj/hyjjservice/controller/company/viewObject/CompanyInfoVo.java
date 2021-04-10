package com.hyjj.hyjjservice.controller.company.viewObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("表格中显示的企业视图模型")
public class CompanyInfoVo {

    @ApiModelProperty("主键id，点击企业名字查询企业详情时，传这个id")
    private Long id;

    @ApiModelProperty("企业名称")
    private String comName;

    @Override
    public String toString() {
        return "CompanyInfoVo{" +
                "id=" + id +
                ", comName='" + comName + '\'' +
                ", comCode='" + comCode + '\'' +
                ", comComtype='" + comComtype + '\'' +
                ", industryName='" + industryName + '\'' +
                ", comAddressCounty='" + comAddressCounty + '\'' +
                ", comBusinessstatus='" + comBusinessstatus + '\'' +
                ", comApply='" + comApply + '\'' +
                '}';
    }

    @ApiModelProperty("组织机构代码")
    private String comCode;

    @ApiModelProperty("单位类型")
    private String comComtype;

    @ApiModelProperty("所属行业")
    private String industryName;

    @ApiModelProperty("所属区县")
    private String comAddressCounty;

    @ApiModelProperty("营业状态")
    private String comBusinessstatus;

    @ApiModelProperty("批复")
    private String comApply = "审核";

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComComtype() {
        return comComtype;
    }

    public void setComComtype(String comComtype) {
        this.comComtype = comComtype;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getComAddressCounty() {
        return comAddressCounty;
    }

    public void setComAddressCounty(String comAddressCounty) {
        this.comAddressCounty = comAddressCounty;
    }

    public String getComBusinessstatus() {
        return comBusinessstatus;
    }

    public void setComBusinessstatus(String comBusinessstatus) {
        this.comBusinessstatus = comBusinessstatus;
    }

    public String getComApply() {
        return comApply;
    }

    public void setComApply(String comApply) {
        this.comApply = comApply;
    }
}
