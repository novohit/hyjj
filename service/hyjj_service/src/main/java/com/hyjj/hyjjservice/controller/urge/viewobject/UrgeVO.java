package com.hyjj.hyjjservice.controller.urge.viewobject;

public class UrgeVO {
    Long id;
    String sendUser;
    String sendGroup;

    public UrgeVO(Long id, String sendUser, String sendGroup){
        this.id = id;
        this.sendUser = sendUser;
        this.sendGroup = sendGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getSendGroup() {
        return sendGroup;
    }

    public void setSendGroup(String sendGroup) {
        this.sendGroup = sendGroup;
    }
}
