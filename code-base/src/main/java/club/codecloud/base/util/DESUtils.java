package club.codecloud.base.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

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
    private static final String KEY_ALGORITHM = "DES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     */
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

    private static final String CHARSET_UTF_8 = "UTF-8";

    private static final int KEY_SIZE = 56;

    /**
     * 生成密钥 <br>
     * Java 6 只支持56bit密钥 <br>
     * Bouncy Castle 支持64bit密钥 <br>
     *
     * @return
     */
    public static String getKey() {
        try {

            // 若要使用64bit密钥注意替换
            // 将下述代码中的KeyGenerator.getInstance(CIPHER_ALGORITHM)
            // 替换为KeyGenerator.getInstance(CIPHER_ALGORITHM, "BC")
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // 初始化密钥生成器 若要使用64bit密钥注意替换 将下述代码kg.init(56); 替换为kg.init(64);
            kg.init(KEY_SIZE, new SecureRandom());
            // 生成秘密密钥
            byte[] secretKey = kg.generateKey().getEncoded();
            return Base64.encodeBase64String(secretKey);
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
            Key k = new SecretKeySpec(key, KEY_ALGORITHM);
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
            byte[] dataBytes = data.getBytes(CHARSET_UTF_8);
            byte[] keyBytes = Base64.decodeBase64(key);
            // 执行操作
            return Base64.encodeBase64String(encrypt(dataBytes, keyBytes));
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
            Key k = new SecretKeySpec(key, KEY_ALGORITHM);
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
        byte[] dataBytes = Base64.decodeBase64(data);
        // 还原密钥
        byte[] keyBytes = Base64.decodeBase64(key);
        // 执行操作
        return new String(decrypt(dataBytes, keyBytes), CharsetUtils.CHARSET_UTF_8);
    }

    public static void main(String[] args) {
        String key = DESUtils.getKey();
        System.out.println("key:" + key);

        String data = "world 欢迎你";
        String encryptData = DESUtils.encrypt(data, key);
        System.out.println("encryptData:" + encryptData);

        String decryptData = DESUtils.decrypt(encryptData, key);
        System.out.println("decryptData:" + decryptData);
    }
}
