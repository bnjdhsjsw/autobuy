package com.istone.testbuy.base;

import java.io.Serializable;

/**
 *请求返回基类，result定义为泛型，便于复用
 */
public class BaseModel<T> implements Serializable {

    private int code;
    private String message;
    private String errorCode;
    private T content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
