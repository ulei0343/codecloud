package club.codecloud.base.util.base;

import club.codecloud.base.util.time.DateFormatUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author ulei
 * @date 2018/9/17
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //属性值为null不返回
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat(DateFormatUtils.DATE_TIME_FORMAT));
        // 设置时区为东八区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
       /* objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);*/
    }

    /**
     * 将对象转换成json字符串
     *
     * @param data
     * @return
     */
    public static String toJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error("JsonUtils parse Object to Json error", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象转换成优雅的json字符串
     *
     * @param data
     * @return
     */
    public static String toPrettyJson(Object data) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error("JsonUtils parse Object to pretty Json error", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串转化为对象
     *
     * @param data
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T toObject(String data, Class<T> beanType) {
        try {
            return objectMapper.readValue(data, beanType);
        } catch (IOException e) {
            logger.error("JsonUtils parse Json to Object error", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成对象list
     *
     * @param data
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String data, Class<T> beanType) {
        try {
            return objectMapper.readValue(data, objectMapper.getTypeFactory().constructParametricType(List.class, beanType));
        } catch (IOException e) {
            logger.error("JsonUtils parse Json to List error", e);
        }
        return null;
    }

    /**
     * 将json数据转换成Map对象
     *
     * @param data
     * @return
     */
    public static Map<String, Object> toMap(String data) {
        try {
            return objectMapper.readValue(data, Map.class);
        } catch (IOException e) {
            logger.error("JsonUtils parse Json to Map error", e);
        }
        return null;
    }
}
