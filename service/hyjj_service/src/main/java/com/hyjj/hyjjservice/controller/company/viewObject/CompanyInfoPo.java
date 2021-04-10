package com.hyjj.hyjjservice.controller.company.viewObject;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CompanyInfoPo {

    @Pattern(regexp = "^[0-1]{1,10}$")
    @NotNull
    private String comTypes;

    @Pattern(regexp = "^[0-1]{1,100}$")
    @NotNull
    private String industrys;

    private String county;

    private String name;

    @Override
    public String toString() {
        return "CompanyInfoPo{" +
                "comTypes='" + comTypes + '\'' +
                ", industrys='" + industrys + '\'' +
                ", county='" + county + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getComTypes() {
        return comTypes;
    }

    public void setComTypes(String comTypes) {
        this.comTypes = comTypes;
    }

    public String getIndustrys() {
        return industrys;
    }

    public void setIndustrys(String industrys) {
        this.industrys = industrys;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
