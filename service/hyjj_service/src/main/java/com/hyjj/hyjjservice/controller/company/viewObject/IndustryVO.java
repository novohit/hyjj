package com.hyjj.hyjjservice.controller.company.viewObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("行业对象")
public class IndustryVO {


    @ApiModelProperty("行业对应的id，之后根据行业类型查询的时候传这个id")
    private Integer id;


    @ApiModelProperty("行业名字")
    private String name;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }




}
