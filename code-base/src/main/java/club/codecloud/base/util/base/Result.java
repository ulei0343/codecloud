package club.codecloud.base.util.base;

import club.codecloud.base.constant.ResultCode;

import java.io.Serializable;

/**
 * 封装统一响应消息体
 *
 * @author ulei
 */
public final class Result implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
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

    private Result(ResultCode resultCode, String errorMsg) {
        this.code = resultCode.getCode();
        this.msg = errorMsg;
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

    public static Result error(ResultCode resultCode, String errorMsg) {
        return new Result(resultCode, errorMsg);
    }
}
