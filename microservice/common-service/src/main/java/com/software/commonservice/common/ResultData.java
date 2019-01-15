package com.software.commonservice.common;


import java.io.Serializable;


public class ResultData implements Serializable {
    private static final long serialVersionUID = 3313389000108412753L;

    public  static enum Status{
        success,
        error
    }

    private Status status;

    private String message;

    private Object bo;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBo() {
        return bo;
    }

    public void setBo(Object bo) {
        this.bo = bo;
    }
}



