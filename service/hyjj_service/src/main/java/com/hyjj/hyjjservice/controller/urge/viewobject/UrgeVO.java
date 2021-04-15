package com.hyjj.hyjjservice.controller.urge.viewobject;

public class UrgeVO {
    String sendUser;
    String sendGroup;

    public UrgeVO(String sendUser, String sendGroup){
        this.sendUser = sendUser;
        this.sendGroup = sendGroup;
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
