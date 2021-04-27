package com.hyjj.hyjjservice.controller.statistic.datatransferobject;

import java.util.List;

public class GetStatisticInfoDTO {
    List<Integer> years;
    String areaName;
    Long targetId;

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
