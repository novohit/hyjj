package com.hyjj.hyjjservice.dataobject;

import java.util.Date;

public class UrgeData {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.send_user
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String sendUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.send_group
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String sendGroup;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.accept_group
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String acceptGroup;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.create_date
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.is_read
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Byte isRead;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.reat_date
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Date reatDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.gmt_create
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.gmt_modified
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column urge_data.web_url
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    private String webUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.id
     *
     * @return the value of urge_data.id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.id
     *
     * @param id the value for urge_data.id
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.send_user
     *
     * @return the value of urge_data.send_user
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.send_user
     *
     * @param sendUser the value for urge_data.send_user
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setSendUser(String sendUser) {
        this.sendUser = sendUser == null ? null : sendUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.send_group
     *
     * @return the value of urge_data.send_group
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getSendGroup() {
        return sendGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.send_group
     *
     * @param sendGroup the value for urge_data.send_group
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setSendGroup(String sendGroup) {
        this.sendGroup = sendGroup == null ? null : sendGroup.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.accept_group
     *
     * @return the value of urge_data.accept_group
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getAcceptGroup() {
        return acceptGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.accept_group
     *
     * @param acceptGroup the value for urge_data.accept_group
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setAcceptGroup(String acceptGroup) {
        this.acceptGroup = acceptGroup == null ? null : acceptGroup.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.create_date
     *
     * @return the value of urge_data.create_date
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.create_date
     *
     * @param createDate the value for urge_data.create_date
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.is_read
     *
     * @return the value of urge_data.is_read
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Byte getIsRead() {
        return isRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.is_read
     *
     * @param isRead the value for urge_data.is_read
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.reat_date
     *
     * @return the value of urge_data.reat_date
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Date getReatDate() {
        return reatDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.reat_date
     *
     * @param reatDate the value for urge_data.reat_date
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setReatDate(Date reatDate) {
        this.reatDate = reatDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.gmt_create
     *
     * @return the value of urge_data.gmt_create
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.gmt_create
     *
     * @param gmtCreate the value for urge_data.gmt_create
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.gmt_modified
     *
     * @return the value of urge_data.gmt_modified
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.gmt_modified
     *
     * @param gmtModified the value for urge_data.gmt_modified
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column urge_data.web_url
     *
     * @return the value of urge_data.web_url
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column urge_data.web_url
     *
     * @param webUrl the value for urge_data.web_url
     *
     * @mbg.generated Wed Dec 16 22:56:19 CST 2020
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }
}