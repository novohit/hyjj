package com.hyjj.util.responce;

import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.tool.ResultCode;

import java.util.HashMap;
import java.util.Map;

public class CommonReturnType {


    private Integer code;
    private String errMsg;
    private short successful;
    private Map<String, Object> data = new HashMap<>();

    /**
     * 成功静态方法
     * @return  返回成功信息
     */
    public static CommonReturnType ok() {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setCode(ResultCode.SUCCESS);
        commonReturnType.setSuccessful((short)1);
        return commonReturnType;
    }

    /**
     * 失败静态方法
     */
    public static CommonReturnType error() {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setCode(ResultCode.ERROR);
        commonReturnType.setSuccessful((short)0);
        return commonReturnType;
    }

    public static CommonReturnType error(EmBusinessError emBusinessError) {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setCode(emBusinessError.getErrCode());
        commonReturnType.setErrMsg(emBusinessError.getErrMsg());
        commonReturnType.setSuccessful((short)0);
        return commonReturnType;
    }


    public CommonReturnType add(String key,Object msg){
        this.data.put(key,msg);
        return this;
    }


    public Integer getCode() {
        return code;
    }

    public CommonReturnType setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public CommonReturnType setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public short getSuccessful() {
        return successful;
    }

    public CommonReturnType setSuccessful(short successful) {
        this.successful = successful;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public CommonReturnType setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}