package com.hyjj.hyjjservice.dataobject;

import java.util.Date;

public class Business {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.id
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.business_text
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    private String businessText;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.cominfo_id
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    private Long cominfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.gmt_create
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.gmt_modified
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.id
     *
     * @return the value of business.id
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.id
     *
     * @param id the value for business.id
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.business_text
     *
     * @return the value of business.business_text
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public String getBusinessText() {
        return businessText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.business_text
     *
     * @param businessText the value for business.business_text
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public void setBusinessText(String businessText) {
        this.businessText = businessText == null ? null : businessText.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.cominfo_id
     *
     * @return the value of business.cominfo_id
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public Long getCominfoId() {
        return cominfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.cominfo_id
     *
     * @param cominfoId the value for business.cominfo_id
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public void setCominfoId(Long cominfoId) {
        this.cominfoId = cominfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.gmt_create
     *
     * @return the value of business.gmt_create
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.gmt_create
     *
     * @param gmtCreate the value for business.gmt_create
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.gmt_modified
     *
     * @return the value of business.gmt_modified
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.gmt_modified
     *
     * @param gmtModified the value for business.gmt_modified
     *
     * @mbg.generated Fri Jan 01 15:29:50 CST 2021
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}