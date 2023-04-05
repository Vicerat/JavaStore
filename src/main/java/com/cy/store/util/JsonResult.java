package com.cy.store.util;

import jdk.nashorn.internal.ir.ThrowNode;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    private Integer state;
    private String message;
    //表示泛型，不确定类型，所有类也要标记为泛型
    private E data;

    public JsonResult() {

    }

    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
