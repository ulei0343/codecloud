package club.codecloud.base.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author ulei
 * @date 2018/6/29
 */
public final class RSASignature {

    private static final String RSA = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * RSA签名
     *
     * @param data       待签名数据
     * @param privateKey 商户私钥
     * @return 签名值
     */
    public static String sign(String data, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
            Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));

            byte[] signed = signature.sign();

            return Base64.encodeBase64String(signed);
        } catch (Exception e) {
            throw new RuntimeException("RSASign签名错误，错误信息：", e);
        }
    }

    /**
     * RSA验签检查
     *
     * @param data      待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @return 布尔值
     */
    public static boolean verify(String data, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            throw new RuntimeException("RSASign验签错误，错误信息：", e);
        }
    }

    public static void main(String[] args) {
        KeyPair keyPair = RSAUtils.getKeyPair();
        String publicKey = RSAUtils.getPublicKey(keyPair);
        System.out.println(publicKey);
        String privateKey = RSAUtils.getPrivateKey(keyPair);
        System.out.println(privateKey);

        String data = "world 欢迎你";
        String sign = RSASignature.sign(data, privateKey);
        System.out.println(sign);

        boolean verify = RSASignature.verify(data, sign, publicKey);
        System.out.println(verify);

    }
}
