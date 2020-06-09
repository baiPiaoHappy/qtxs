package com.base.bean;


import lombok.Data;

/**
 * @description:
 * @author: 小猴子
 * @date: 2019-11-07 15:04
 */
@Data
public class ApiMsg {

    private int code;

    private String msg;

    private Object data;

    public ApiMsg() {
    }
    public ApiMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
