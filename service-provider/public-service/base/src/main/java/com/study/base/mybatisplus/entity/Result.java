package com.study.base.mybatisplus.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class Result<T> {


    public static final int OK = 200;

    private T data;
    private int code;
    private String msg;




    public static <T> Result ok(String msg){
        Result<T> result = new Result<T>();
        result.code = OK;
        result.msg = msg;
        result.data = null;
        return result;
    }

    public static <T> Result ok(T data){
        Result<T> result = new Result<T>();
        result.code = OK;
        result.msg = "ok";
        result.data = data;
        return result;
    }

    public static <T> Result ok(String msg,T data){
        Result<T> result = new Result<T>();
        result.code = OK;
        result.data = data;
        result.msg = msg;
        return result;
    }

    public static <T> Result fail(int errCode,String errMsg){
        Result<T> result = new Result<T>();
        result.code = errCode;
        result.msg = errMsg;
        result.data = null;
        return result;
    }

    public static <T> Result fail(String errMsg,T data){
        Result<T> result = new Result<T>();
        result.code = -1;
        result.msg = errMsg;
        result.data = data;
        return result;
    }

    public static <T> Result fail(int errCode,String errMsg,T data){
        Result<T> result = new Result<T>();
        result.data = data;
        result.code = errCode;
        result.msg = errMsg;
        return result;
    }


    public static final int AUTH_SUCCESS = 208;
    public static final int AUTH_FAIL = 209;
    /**
     * 认证成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result authSuccess(T data){
        Result<T> result = new Result<T>();
        result.data = data;
        result.code = AUTH_SUCCESS;
        result.msg = "认证成功";
        return result;
    }

    /**
     * 认证成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result authFail(T data){
        Result<T> result = new Result<T>();
        result.data = data;
        result.code = AUTH_FAIL;
        result.msg = "认证失败";
        return result;
    }


    public String toString(){
        return JSON.toJSONString(this);
    }
}
