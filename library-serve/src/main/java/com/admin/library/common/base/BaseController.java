package com.admin.library.common.base;


/**
 * 基础控制器
 *
 * @author SouthMaple
 * @since 2021-04-07 20:46:31
 */
public class BaseController {

    protected <T> R<T> result(T data) {
        return R.result(data);
    }

    protected <T> R<T> result(long code,String msg) {
        return R.result(code,msg);
    }

    protected <T> R<T> result(T data, String msg) {
        return R.result(data);
    }
}
