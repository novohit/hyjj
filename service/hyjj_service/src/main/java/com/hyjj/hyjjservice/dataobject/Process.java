package com.hyjj.hyjjservice.dataobject;

import java.util.Date;

public class Process {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.id
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.version_num
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private Integer versionNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.process_name
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private String processName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.creat_user
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private String creatUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.creat_time
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private Date creatTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.update_user
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private String updateUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.update_time
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.remark
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.prosess_description
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private String prosessDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.gmt_create
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process.gmt_modified
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.id
     *
     * @return the value of process.id
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.id
     *
     * @param id the value for process.id
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.version_num
     *
     * @return the value of process.version_num
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public Integer getVersionNum() {
        return versionNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.version_num
     *
     * @param versionNum the value for process.version_num
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.process_name
     *
     * @return the value of process.process_name
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.process_name
     *
     * @param processName the value for process.process_name
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.creat_user
     *
     * @return the value of process.creat_user
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public String getCreatUser() {
        return creatUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.creat_user
     *
     * @param creatUser the value for process.creat_user
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setCreatUser(String creatUser) {
        this.creatUser = creatUser == null ? null : creatUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.creat_time
     *
     * @return the value of process.creat_time
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.creat_time
     *
     * @param creatTime the value for process.creat_time
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.update_user
     *
     * @return the value of process.update_user
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.update_user
     *
     * @param updateUser the value for process.update_user
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.update_time
     *
     * @return the value of process.update_time
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.update_time
     *
     * @param updateTime the value for process.update_time
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.remark
     *
     * @return the value of process.remark
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.remark
     *
     * @param remark the value for process.remark
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.prosess_description
     *
     * @return the value of process.prosess_description
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public String getProsessDescription() {
        return prosessDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.prosess_description
     *
     * @param prosessDescription the value for process.prosess_description
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setProsessDescription(String prosessDescription) {
        this.prosessDescription = prosessDescription == null ? null : prosessDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.gmt_create
     *
     * @return the value of process.gmt_create
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.gmt_create
     *
     * @param gmtCreate the value for process.gmt_create
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process.gmt_modified
     *
     * @return the value of process.gmt_modified
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process.gmt_modified
     *
     * @param gmtModified the value for process.gmt_modified
     *
     * @mbg.generated Sun Mar 07 15:29:25 CST 2021
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}