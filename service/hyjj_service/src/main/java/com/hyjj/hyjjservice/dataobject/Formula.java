package com.hyjj.hyjjservice.dataobject;

import java.util.Date;

public class Formula {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.formula_type
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Byte formulaType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.target_key_id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Long targetKeyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.operator
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String operator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.expression
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String expression;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.remark
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.error_tip
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String errorTip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.report_template_id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Long reportTemplateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.gmt_create
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column formula.gmt_modified
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.id
     *
     * @return the value of formula.id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.id
     *
     * @param id the value for formula.id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.formula_type
     *
     * @return the value of formula.formula_type
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Byte getFormulaType() {
        return formulaType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.formula_type
     *
     * @param formulaType the value for formula.formula_type
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setFormulaType(Byte formulaType) {
        this.formulaType = formulaType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.target_key_id
     *
     * @return the value of formula.target_key_id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Long getTargetKeyId() {
        return targetKeyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.target_key_id
     *
     * @param targetKeyId the value for formula.target_key_id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setTargetKeyId(Long targetKeyId) {
        this.targetKeyId = targetKeyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.operator
     *
     * @return the value of formula.operator
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.operator
     *
     * @param operator the value for formula.operator
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.expression
     *
     * @return the value of formula.expression
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getExpression() {
        return expression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.expression
     *
     * @param expression the value for formula.expression
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setExpression(String expression) {
        this.expression = expression == null ? null : expression.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.remark
     *
     * @return the value of formula.remark
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.remark
     *
     * @param remark the value for formula.remark
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.error_tip
     *
     * @return the value of formula.error_tip
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getErrorTip() {
        return errorTip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.error_tip
     *
     * @param errorTip the value for formula.error_tip
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setErrorTip(String errorTip) {
        this.errorTip = errorTip == null ? null : errorTip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.report_template_id
     *
     * @return the value of formula.report_template_id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Long getReportTemplateId() {
        return reportTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.report_template_id
     *
     * @param reportTemplateId the value for formula.report_template_id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setReportTemplateId(Long reportTemplateId) {
        this.reportTemplateId = reportTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.gmt_create
     *
     * @return the value of formula.gmt_create
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.gmt_create
     *
     * @param gmtCreate the value for formula.gmt_create
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column formula.gmt_modified
     *
     * @return the value of formula.gmt_modified
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column formula.gmt_modified
     *
     * @param gmtModified the value for formula.gmt_modified
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}