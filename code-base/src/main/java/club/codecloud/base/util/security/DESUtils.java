package club.codecloud.base.util.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author ulei
 * @date 2018/6/29
 * <p>
 * DES算法加解密
 */
public final class DESUtils {

    /**
     * 密钥算法 <br>
     * Java 6 只支持56bit密钥 <br>
     * Bouncy Castle 支持64bit密钥
     */
    private static final String DES = "DES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     */
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

    private static final int KEY_SIZE = 56;

    private static KeyGenerator keyGenerator = null;

    static {
        try {
            keyGenerator = KeyGenerator.getInstance(DES);
        } catch (Exception e) {
            throw new RuntimeException("init DESUtils error", e);
        }
    }

    /**
     * 生成密钥 <br>
     * Java 6 只支持56bit密钥 <br>
     * Bouncy Castle 支持64bit密钥 <br>
     *
     * @return
     */
    public static String getKey() {
        try {
            keyGenerator.init(KEY_SIZE, new SecureRandom());
            // 生成密钥
            byte[] secretKey = keyGenerator.generateKey().getEncoded();
            return Base64.getEncoder().encodeToString(secretKey);
        } catch (Exception e) {
            throw new RuntimeException("获取DES_Key错误，错误信息：", e);
        }
    }

    /**
     * 数据加密，算法（DES）
     *
     * @param data 要进行加密的数据
     * @return 加密后的数据
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            // 还原密钥
            Key k = new SecretKeySpec(key, DES);
            // 实例化
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, k);
            // 执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("DES加密错误，错误信息：", e);
        }
    }

    /**
     * 数据加密，算法（DES）
     *
     * @param data 要进行加密的数据
     * @return 加密后的数据
     */
    public static String encrypt(String data, String key) {
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] keyBytes = Base64.getDecoder().decode(key);
            // 执行操作
            return Base64.getEncoder().encodeToString(encrypt(dataBytes, keyBytes));
        } catch (Exception e) {
            throw new RuntimeException("DES加密错误，错误信息：", e);
        }
    }

    /**
     * 数据解密，算法（DES）
     *
     * @param data 加密数据
     * @return 解密后的数据
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            // 还原密钥
            Key k = new SecretKeySpec(key, DES);
            // 实例化
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为加密模式
            cipher.init(Cipher.DECRYPT_MODE, k);
            // 执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("DES解密错误，错误信息：", e);
        }
    }

    /**
     * 数据解密，算法（DES）
     *
     * @param data 加密数据
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key) {
        // 解析数据
        byte[] dataBytes = Base64.getDecoder().decode(data);
        // 还原密钥
        byte[] keyBytes = Base64.getDecoder().decode(key);
        // 执行操作
        return new String(decrypt(dataBytes, keyBytes), StandardCharsets.UTF_8);
    }
}
