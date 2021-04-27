package com.hyjj.hyjjservice.controller.settings.viewObject;

public class FormulaListVO {
    private String id;
    private String number;
    private String targetKeyId;
    private String operator;
    private String expression;
    private String errorTip;
    private String remark;
    private Integer formulaType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTargetKeyId() {
        return targetKeyId;
    }

    public void setTargetKeyId(String targetKeyId) {
        this.targetKeyId = targetKeyId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getErrorTip() {
        return errorTip;
    }

    public void setErrorTip(String errorTip) {
        this.errorTip = errorTip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(Integer formulaType) {
        this.formulaType = formulaType;
    }
}
