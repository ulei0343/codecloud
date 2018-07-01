package club.codecloud.base.config.encrypt;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encrypt")
public class EncryptProperties {

    /**
     * AES加密KEY
     */
    private String key;

    /**
     * 开启调试模式，调试模式下不进行加解密操作
     */
    private boolean debug;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
