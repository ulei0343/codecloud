package club.codecloud.base.util.security;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author ulei
 * @date 2018/6/29
 */
public final class AESUtils {

    /**
     * 密钥算法
     */
    private static final String AES = "AES";
    private static final int KEY_SIZE = 128;
    /**
     * 加密/解密算法/工作模式/填充方法
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5PADDING";

    private static KeyGenerator keyGenerator = null;

    static {
        try {
            keyGenerator = KeyGenerator.getInstance(AES);
        } catch (Exception e) {
            throw new RuntimeException("init AESUtils error", e);
        }
    }

    /**
     * 获取密钥
     *
     * @return
     * @throws Exception
     */
    public static String getKey() {
        try {
            //实例化
            //AES 要求密钥长度为128位、192位或256位
            keyGenerator.init(KEY_SIZE, new SecureRandom());
            //生成密钥
            byte[] secretKey = keyGenerator.generateKey().getEncoded();
            return Base64.getEncoder().encodeToString(secretKey);
        } catch (Exception e) {
            throw new RuntimeException("获取AES_Key错误，错误信息：", e);
        }
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return bytes[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            //还原密钥
            Key k = new SecretKeySpec(key, AES);
            /**
             * 实例化
             * 使用PKCS7Padding填充方式，按如下方式实现
             * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
             */
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, k);
            //执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("AES加密错误，错误信息：", e);
        }
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密数据
     * @throws Exception
     */
    public static String encrypt(String data, String key) {
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] keyBytes = Base64.getDecoder().decode(key);
            return Base64.getEncoder().encodeToString(encrypt(dataBytes, keyBytes));
        } catch (Exception e) {
            throw new RuntimeException("AES加密错误，错误信息：", e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) {
        try {
            //还原密钥
            Key k = new SecretKeySpec(key, AES);
            /**
             * 实例化
             * 使用PKCS7Padding填充方式，按如下方式实现
             * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
             */
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //初始化，设置解密模式
            cipher.init(Cipher.DECRYPT_MODE, k);
            //执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("AES解密错误，错误信息：", e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密数据
     * @throws Exception
     */
    public static String decrypt(String data, String key) {
        // 解析数据
        byte[] dataBytes = Base64.getDecoder().decode(data);
        // 还原密钥
        byte[] keyBytes = Base64.getDecoder().decode(key);
        // 执行操作
        return new String(decrypt(dataBytes, keyBytes), StandardCharsets.UTF_8);

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String key = AESUtils.getKey();
        System.out.println("key:" + key);

        String data = "world 欢迎你";
        String encryptData = AESUtils.encrypt(data, key);
        System.out.println("encryptData:" + encryptData);

        String decryptData = AESUtils.decrypt(encryptData, key);
        System.out.println("decryptData:" + decryptData);
    }

}
