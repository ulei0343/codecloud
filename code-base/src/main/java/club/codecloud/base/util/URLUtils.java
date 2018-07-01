package club.codecloud.base.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author ulei
 * @date 2018/4/15
 */
public class URLUtils {

    private static final Logger logger = LoggerFactory.getLogger(URLUtils.class);

    /**
     * 编码字符
     *
     * @param content 被编码内容
     * @param charset 编码
     * @return 编码后的字符
     */
    public static String encode(String content, Charset charset) {
        return encode(content, charset.name());
    }

    /**
     * 编码字符
     *
     * @param content    被编码内容
     * @param charsetStr 编码
     * @return 编码后的字符
     */
    public static String encode(String content, String charsetStr) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        String encodeContent = null;
        try {
            encodeContent = URLEncoder.encode(content, charsetStr);
        } catch (UnsupportedEncodingException e) {
            logger.error("encode error", e);
        }
        return encodeContent;
    }

    /**
     * 解码字符
     *
     * @param content 被解码内容
     * @param charset 编码
     * @return 编码后的字符
     */
    public static String decode(String content, Charset charset) {
        return decode(content, charset.name());
    }

    /**
     * 解码字符
     *
     * @param content    被解码内容
     * @param charsetStr 编码
     * @return 编码后的字符
     */
    public static String decode(String content, String charsetStr) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        String encodeContent = null;
        try {
            encodeContent = URLDecoder.decode(content, charsetStr);
        } catch (UnsupportedEncodingException e) {
            logger.error("decode error", e);
        }
        return encodeContent;
    }

    /**
     * 将Map形式的Form表单数据转换为Url参数形式<br>
     * 编码键值对
     *
     * @param paramMap 表单数据
     * @return url参数
     */
    public static String toParams(Map<String, String> paramMap) {
        if (paramMap == null) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Map.Entry<String, String> item : paramMap.entrySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("&");
            }
            sb.append(encode(item.getKey(), StandardCharsets.UTF_8)).append("=").append(encode(item.getValue(), StandardCharsets.UTF_8));
        }
        return sb.toString();
    }

    /**
     * 将表单数据字符串加到URL中（用于GET表单提交）
     *
     * @param url         URL
     * @param queryString 表单数据字符串
     * @return 拼接后的字符串
     */
    public static String urlWithForm(String url, String queryString) {
        if (StringUtils.isBlank(queryString)) {
            return url;
        }
        if (url.contains("?")) {
            // 原URL已经带参数
            url += "&" + queryString;
        } else {
            url += url.endsWith("?") ? queryString : "?" + queryString;
        }
        return url;
    }

    /**
     * 将表单数据加到URL中（用于GET表单提交）
     *
     * @param url  URL
     * @param form 表单数据
     * @return 合成后的URL
     */
    public static String urlWithForm(String url, Map<String, String> form) {
        final String queryString = toParams(form);
        return urlWithForm(url, queryString);
    }


}
