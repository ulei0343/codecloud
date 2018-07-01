package club.codecloud.base.util;

import club.codecloud.base.constant.ResultCode;

import java.io.Serializable;

/**
 * 封装统一响应消息体
 */
public final class Result implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    private Result(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }

    private Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    private Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result error(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public static Result error(ResultCode resultCode, Object data) {
        return new Result(resultCode, data);
    }


}
