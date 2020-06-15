package com.web.controller;

/**
 * http接口返回对象封装
 */

public class Response {
    public Response(String status , String code, String msg, Object data){
        this.status=status;
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    String code;
    String msg;
    String status;
    Object data;

}
