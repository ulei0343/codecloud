package club.codecloud.base.util.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author ulei
 * @date 2018/6/29
 * <p>
 * Base64算法加解密
 */
public final class Base64Utils {

    /**
     * BASE64编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encode(byte[] data) {
        try {
            return Base64.getEncoder().encode(data);
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }

    /**
     * BASE64编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String data) {
        try {
            return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }


    /**
     * BASE64解码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decode(byte[] data) {
        try {
            return Base64.getDecoder().decode(data);
        } catch (Exception e) {
            throw new RuntimeException("Base64解密错误，错误信息：", e);
        }
    }

    /**
     * BASE64解码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(String data) {
        try {
            return new String(Base64.getDecoder().decode(data));
        } catch (Exception e) {
            throw new RuntimeException("Base64解密错误，错误信息：", e);
        }
    }
}
