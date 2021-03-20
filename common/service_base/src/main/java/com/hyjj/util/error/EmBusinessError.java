package com.hyjj.util.error;

public enum EmBusinessError implements CommonError {

    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),
    Image_Format_Error(10003, "图片格式错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "账号或密码不正确"),
    USER_NOT_LOGIN(20003, "用户还未登陆"),
    USER_DONOT_HVER_PERMISSION(20004, "用户没有该权限");


    private int errCode;
    private String errMsg;


    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private EmBusinessError(int errCode) {
        this.errCode = errCode;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String essMsg) {
        this.errMsg = essMsg;
        return this;
    }
}
