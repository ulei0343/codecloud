package club.codecloud.demo.common.constant;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author: ulei
 * @date: 2019-05-29
 */
public enum UserStatusEnum implements BaseCodeEnum {
    /**
     * 正常用户
     */
    NORMAL(0, "正常"),

    /**
     * 已注销，不允许使用
     */
    LOGOUT(-1, "注销");

    private int code;
    private String value;

    private static final Map<Integer, UserStatusEnum> ALL = Maps.newHashMap();

    static {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            ALL.put(userStatusEnum.getCode(), userStatusEnum);
        }
    }

    UserStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserStatusEnum{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }

    public static UserStatusEnum parse(int code) {
        return ALL.get(code);
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }}
