package com.fantasy.rivendell.service.domain;


/**
 * Created by lingyao on 16/5/26.
 */

public class ApiResult<T> {

    /**
     * api调用是否成功
     */
    private boolean success = true;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 返回结果
     */
    private T result;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setFailResult(String errorMsg) {
        success = false;
        this.errorMsg = errorMsg;
    }

    public void setSuccessResult(T result) {
        success = true;
        this.result = result;
    }
}
