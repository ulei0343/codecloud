package com.aicity.common.util.security;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
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
    private static final String RSA = "RSA";

    private static final String RSA_PADDING = "RSA/ECB/PKCS1Padding";

    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 生成并返回RSA密钥对
     *
     * @return
     */
    public static KeyPair getKeyPair() {
        return getKeyPair(KEY_SIZE);
    }

    /**
     * 生成并返回RSA密钥对
     *
     * @return
     */
    public static KeyPair getKeyPair(int size) {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance(RSA);
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            return keyPairGen.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成RSA钥匙对出错", e);
        }
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

    private static RSAPrivateKey getPrivateKey(String privateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException("RSA加密获取私钥出错", e);
        }
    }

    private static RSAPublicKey getPublicKey(String publicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPublicKey) keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("RSA加密获取公钥出错", e);
        }
    }


    /**
     * 使用指定的公钥加密数据。
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据。
     * @return 加密后的数据。
     */
    public static byte[] encryptByPublicKey(RSAPublicKey publicKey, byte[] data) {

        try {
            Cipher cipher = Cipher.getInstance(RSA_PADDING);
            // 对数据加密
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
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
    public static String encryptByPublicKey(String publicKey, String data) {
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            return Base64.getEncoder().encodeToString(encryptByPublicKey(getPublicKey(publicKey), dataBytes));
        } catch (Exception e) {
            throw new RuntimeException("RSA加密出错", e);
        }
    }

    /**
     * 使用指定的私钥加密数据
     *
     * @param privateKey 给定的私钥
     * @param data       要加密的数据
     * @return 加密后的数据。
     */
    public static byte[] encryptByPrivateKey(RSAPrivateKey privateKey, byte[] data) {

        try {
            Cipher cipher = Cipher.getInstance(RSA_PADDING);
            // 对数据加密
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("RSA加密出错", e);
        }
    }

    /**
     * 使用指定的公钥加密数据
     *
     * @param privateKey 给定的私
     * @param data       要加密的数据
     * @return 加密后的数据。
     */
    public static String encryptByPrivateKey(String privateKey, String data) {
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            return Base64.getEncoder().encodeToString(encryptByPrivateKey(getPrivateKey(privateKey), dataBytes));
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
    public static byte[] decryptByPrivateKey(RSAPrivateKey privateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_PADDING);
            // 对数据解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
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
    public static String decryptByPrivateKey(String privateKey, String data) {
        try {
            byte[] dataBytes = Base64.getDecoder().decode(data);
            return new String(decryptByPrivateKey(getPrivateKey(privateKey), dataBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密出错", e);
        }
    }

    /**
     * 使用指定的公钥解密数据。
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据
     * @return 解密后的数据。
     */
    public static byte[] decryptByPublicKey(RSAPublicKey publicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_PADDING);
            // 对数据解密
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密出错", e);
        }
    }

    /**
     * 使用指定的公钥解密数据。
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据。
     * @return 解密后的数据。
     */
    public static String decryptByPublicKey(String publicKey, String data) {
        try {
            byte[] dataBytes = Base64.getDecoder().decode(data);
            return new String(decryptByPublicKey(getPublicKey(publicKey), dataBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密出错", e);
        }
    }
}
