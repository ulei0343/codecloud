package club.codecloud.base.util;

import club.codecloud.base.constant.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json转换工具
 */
public final class JsonUtils {

    private static final String CODE = "code";
    private static final String ERR_MSG = "errMsg";
    private static final String DATA = "data";


    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String genErrorResult(ResultCode code) {
        return null;
    }

    public static String genResult(Object data) {
        return null;
    }
}
