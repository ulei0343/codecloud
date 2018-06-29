package club.codecloud.base.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author ulei
 * @date 2018/6/29
 */
public final class RSAUtils {

    /**
     * 算法名称
     */
    private static final String KEY_ALGORITHM = "RSA";

    private static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 1024;
    /**
     * 默认的安全服务提供者
     */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    private static KeyPairGenerator keyPairGen = null;
    private static KeyFactory keyFactory = null;

    static {
        try {
            keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM, DEFAULT_PROVIDER);
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, DEFAULT_PROVIDER);
        } catch (Exception e) {
            throw new RuntimeException("init RSAUtils error", e);
        }
    }

    /**
     * 生成并返回RSA密钥对
     *
     * @return
     */
    private static KeyPair getKeyPair() {
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        return keyPairGen.genKeyPair();
    }

    /**
     * 获取公钥
     *
     * @param keyPair
     * @return
     */
    public static String getPublicKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    /**
     * 获取私钥
     *
     * @param keyPair
     * @return
     */
    public static String getPrivateKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }


    /**
     * 使用指定的公钥加密数据。
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据。
     * @return 加密后的数据。
     */
    public static byte[] encrypt(byte[] publicKey, byte[] data) {

        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
            // 生成公钥
            PublicKey key = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, DEFAULT_PROVIDER);
            // 对数据加密
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("RSA加密出错", e);
        }
    }

    /**
     * 使用指定的公钥加密数据。
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据。
     * @return 加密后的数据。
     */
    public static String encrypt(String publicKey, String data) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
            byte[] dataBytes = data.getBytes(CHARSET_UTF_8);
            return Base64.getEncoder().encodeToString(encrypt(publicKeyBytes, dataBytes));
        } catch (Exception e) {
            throw new RuntimeException("RSA加密出错", e);
        }
    }

    /**
     * 使用指定的私钥解密数据。
     *
     * @param privateKey 给定的私钥。
     * @param data       要加密的数据。
     * @return 解密后的数据。
     */
    public static byte[] decrypt(byte[] privateKey, byte[] data) {
        try {
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
            // 生成私钥
            PrivateKey key = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, DEFAULT_PROVIDER);
            // 对数据解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密出错", e);
        }
    }

    /**
     * 使用指定的私钥解密数据。
     *
     * @param privateKey 给定的私钥。
     * @param data       要加密的数据。
     * @return 解密后的数据。
     */
    public static String decrypt(String privateKey, String data) {
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
            byte[] dataBytes = Base64.getDecoder().decode(data);
            return new String(decrypt(privateKeyBytes, dataBytes), CHARSET_UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密出错", e);
        }
    }


    public static void main(String[] args) {
        KeyPair keyPair = RSAUtils.getKeyPair();
        String publicKey = getPublicKey(keyPair);
        System.out.println(publicKey);
        String privateKey = getPrivateKey(keyPair);
        System.out.println(privateKey);

        String data = "world 欢迎你";
        String encryptData = RSAUtils.encrypt(publicKey, data);
        System.out.println(encryptData);

        String decryptData = RSAUtils.decrypt(privateKey, encryptData);
        System.out.println(decryptData);


    }
}
