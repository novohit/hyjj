package com.hyjj.util.error;

public interface CommonError {
    /**
     * 获取错误码
     * @return
     */
    int getErrCode();

    /**
     * 获取错误信息
     * @return
     */
    String getErrMsg();

    /**
     * 设置错误信息
     * @param essMsg
     * @return
     */
    CommonError setErrMsg(String essMsg);


}
