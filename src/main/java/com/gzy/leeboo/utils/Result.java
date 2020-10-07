package com.gzy.leeboo.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>基于前后端分离的JSON类型返回值包装类</h1>
 * <b>特点：</b>
 * <br/>1.可自定义返回状态码、返回信息、返回结果数据等。
 * <br/>2.可链式编程。
 */
public class Result implements Serializable {
    /*==================================== 常量 ====================================*/
    private static final int SUCCESS_CODE = 200;// 成功状态码
    private static final int FAILURE_CODE = 400;// 失败状态码
    private static final String SUCCESS_STATUS = "SUCCESS";// 成功状态信息
    private static final String FAILURE_STATUS = "FAILURE";// 失败状态信息

    /*==================================== 属性 ====================================*/
    private String status;// 返回结果的状态
    private Integer code;// 返回结果的状态码
    private String message;// 返回结果的状态信息
    private Map<String, Object> data = new HashMap<>();// 返回结果的数据

    public String getStatus() {
        return status;
    }
    private void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }
    private void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    private void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }
    private void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 操作成功的方法
     *
     * @return 返回的结果
     */
    public static Result success() {
        Result result = new Result();
        result.setStatus(SUCCESS_STATUS);
        result.setCode(SUCCESS_CODE);
        return result;
    }

    /**
     * 操作失败的方法
     *
     * @return 返回的结果
     */
    public static Result failure() {
        Result result = new Result();
        result.setStatus(FAILURE_STATUS);
        result.setCode(FAILURE_CODE);
        return result;
    }

    /**
     * 定义操作返回结果的状态
     *
     * @param status 结果的状态
     * @return 返回的结果
     */
    public Result status(String status) {
        this.setStatus(status);
        return this;
    }

    /**
     * 定义操作返回结果的状态码
     *
     * @param code 结果的状态码
     * @return 返回的结果
     */
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 定义操作返回结果的状态信息
     *
     * @param message 结果的状态信息
     * @return 返回的结果
     */
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 定义操作返回结果的数据
     *
     * @param key   数据存入Map集合的Key值
     * @param value 数据的Value值
     * @return 返回的结果
     */
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * 将所有数据先封装为一个Map集合，再将其定义为操作返回结果的数据
     *
     * @param data 存有返回结果数据的Map集合
     * @return 返回的结果
     */
    public Result data(Map<String, Object> data) {
        this.setData(data);
        return this;
    }
}
