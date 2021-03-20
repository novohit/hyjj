package com.hyjj.hyjjservice.dao.param;

import java.util.List;

public class ComInfoQueryPo {
    private List<Integer> comTypes;
    private List<Integer> industryIds;
    private String county;
    private String name;
    private Long start;
    private Long size;

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<Integer> getComTypes() {
        return comTypes;
    }

    public void setComTypes(List<Integer> comTypes) {
        this.comTypes = comTypes;
    }

    public List<Integer> getIndustryIds() {
        return industryIds;
    }

    public void setIndustryIds(List<Integer> industryIds) {
        this.industryIds = industryIds;
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
