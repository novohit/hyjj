package com.hyjj.hyjjservice.dataobject;

import java.util.Date;

public class Publish {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column publish.id
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column publish.title
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column publish.rich_text
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    private String richText;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column publish.gmt_create
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column publish.gmt_modified
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column publish.id
     *
     * @return the value of publish.id
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column publish.id
     *
     * @param id the value for publish.id
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column publish.title
     *
     * @return the value of publish.title
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column publish.title
     *
     * @param title the value for publish.title
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column publish.rich_text
     *
     * @return the value of publish.rich_text
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public String getRichText() {
        return richText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column publish.rich_text
     *
     * @param richText the value for publish.rich_text
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public void setRichText(String richText) {
        this.richText = richText == null ? null : richText.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column publish.gmt_create
     *
     * @return the value of publish.gmt_create
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column publish.gmt_create
     *
     * @param gmtCreate the value for publish.gmt_create
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column publish.gmt_modified
     *
     * @return the value of publish.gmt_modified
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column publish.gmt_modified
     *
     * @param gmtModified the value for publish.gmt_modified
     *
     * @mbg.generated Sat Jan 09 22:36:47 CST 2021
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}