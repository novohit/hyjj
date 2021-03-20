package com.hyjj.util.error;

import com.hyjj.util.responce.CommonReturnType;

public class BusinessException extends Exception implements CommonError {
    private CommonReturnType commonReturnType;
    private CommonError commonError;

    public BusinessException(CommonReturnType commonReturnType) {
        super();
        this.commonReturnType = commonReturnType;
    }

    public BusinessException(CommonReturnType commonReturnType, CommonError commonError) {
        super();
        this.commonReturnType = commonReturnType;
        commonReturnType.setSuccessful((short) 0);
        commonReturnType.setErrMsg(commonError.getErrMsg());
        commonReturnType.setCode(commonError.getErrCode());
    }

    @Override
    public int getErrCode() {
        return this.commonReturnType.getCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonReturnType.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String essMsg) {
        this.commonReturnType.setErrMsg(essMsg);

        return commonError;
    }


    @Override
    public String toString() {
        return "BusinessException{" +
                "commonReturnType=" + commonReturnType +
                '}';
    }

    public CommonReturnType getCommonReturnType() {
        return commonReturnType;
    }

    public void setCommonReturnType(CommonReturnType commonReturnType) {
        this.commonReturnType = commonReturnType;
    }
}
