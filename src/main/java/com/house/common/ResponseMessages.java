package com.house.common;

import java.io.Serializable;

public class ResponseMessages<T> implements Serializable {
    private int errorCode = 1000;
    private String errorMessage = "success";
    private Boolean success = true;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public ResponseMessages<T> setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ResponseMessages<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public ResponseMessages<T> setErrorMessage(String msg) {
        this.errorMessage = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResponseMessages<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseMessages(int errorCode, String errorMessage, Boolean success) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public ResponseMessages() {
        super();
    }

    public ResponseMessages(int errorCode, String errorMessage, Boolean success, T data) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.success = success;
        this.data = data;
    }

    public ResponseMessages(int errorCode, Boolean success, T data) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.success = success;
        this.data = data;
    }


}
