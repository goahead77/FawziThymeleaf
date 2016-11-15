package cn.fawzi.thymeleaf.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wenqi
 */
@Setter
@Getter
public class ApiResult {
    private int code;

    private String msg;

    private Object data;

    public ApiResult(int code,String msg,Object data) {
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
}
