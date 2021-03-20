package com.hyjj.hyjjservice.dataobject;

import java.util.Date;

public class Permission {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.id
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.name
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.path
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.is_used
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    private Byte isUsed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.permission_value
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    private String permissionValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.gmt_create
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.gmt_modified
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.id
     *
     * @return the value of permission.id
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.id
     *
     * @param id the value for permission.id
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.name
     *
     * @return the value of permission.name
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.name
     *
     * @param name the value for permission.name
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.path
     *
     * @return the value of permission.path
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.path
     *
     * @param path the value for permission.path
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.is_used
     *
     * @return the value of permission.is_used
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public Byte getIsUsed() {
        return isUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.is_used
     *
     * @param isUsed the value for permission.is_used
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public void setIsUsed(Byte isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.permission_value
     *
     * @return the value of permission.permission_value
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public String getPermissionValue() {
        return permissionValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.permission_value
     *
     * @param permissionValue the value for permission.permission_value
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue == null ? null : permissionValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.gmt_create
     *
     * @return the value of permission.gmt_create
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.gmt_create
     *
     * @param gmtCreate the value for permission.gmt_create
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.gmt_modified
     *
     * @return the value of permission.gmt_modified
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.gmt_modified
     *
     * @param gmtModified the value for permission.gmt_modified
     *
     * @mbg.generated Wed Dec 23 19:10:50 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}