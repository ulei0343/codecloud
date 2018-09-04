package club.codecloud.base.constant;

public enum ResultCode {

    SUCCESS(200, "ok"),
    DEFAULT_ERROR(10000, "系统忙，请稍后再试"),
    PARAMETER_ERROR(10001, "参数错误");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
