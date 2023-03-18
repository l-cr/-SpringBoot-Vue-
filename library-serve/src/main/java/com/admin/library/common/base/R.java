package com.admin.library.common.base;

import java.io.Serializable;

/**
 * 统一结果返回类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:31:51
 */
public class R<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    public static final long SUCCESS = 200; // 成功码
    public static final String SUCCESS_MESSAGE = "执行成功"; // 成功消息

    public static final long ERROR = 500; // 失败码
    public static final String ERROR_MESSAGE = "执行失败"; // 失败消息

    /**
     * 状态码
     */
    private long code;

    /**
     * 数据
     */
    private T data;

    /**
     * 信息
     */
    private String msg;

    public R() {
    }

    public R(long code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> R<T> result(T data) {
        if (data == null) {
            return fail(null);
        }
        if (data instanceof Boolean) {
            return (Boolean) data ? ok(null) : fail(null);
        } else {
            return ok(data);
        }
    }

    public static <T> R<T> result(long code,String msg) {
        return new R<>(code, null, msg);
    }

    public static <T> R<T> result(T data, String msg) {
        R<T> r = result(data);
        r.setMsg(msg);
        return r;
    }

    // 执行成功
    public static <T> R<T> ok(T data) {
        return new R<T>(SUCCESS, data, SUCCESS_MESSAGE);
    }

    // 执行失败
    public static <T> R<T> fail(T data) {
        return new R<T>(ERROR, data, ERROR_MESSAGE);
    }


    //================ getter/setter  ==================
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
