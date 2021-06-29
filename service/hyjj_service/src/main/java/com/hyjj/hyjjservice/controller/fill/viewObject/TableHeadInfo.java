package com.hyjj.hyjjservice.controller.fill.viewObject;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement;

public class TableHeadInfo {
    private String AreaName;
    private String OrganizationCode;
    private String CreditCode;
    private String UnitName;

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getOrganizationCode() {
        return OrganizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        OrganizationCode = organizationCode;
    }

    public String getCreditCode() {
        return CreditCode;
    }

    public void setCreditCode(String creditCode) {
        CreditCode = creditCode;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}
