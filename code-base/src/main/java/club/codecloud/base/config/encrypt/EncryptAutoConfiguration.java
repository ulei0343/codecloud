package club.codecloud.base.config.encrypt;

import club.codecloud.base.config.encrypt.advice.DecryptRequestBodyAdvice;
import club.codecloud.base.config.encrypt.advice.EncryptResponseBodyAdvice;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 加解密自动配置
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(EncryptProperties.class)
public class EncryptAutoConfiguration {

    /**
     * 配置请求解密
     *
     * @return
     */
    @Bean
    public EncryptResponseBodyAdvice encryptResponseBodyAdvice() {
        return new EncryptResponseBodyAdvice();
    }

    /**
     * 配置请求加密
     *
     * @return
     */
    @Bean
    public DecryptRequestBodyAdvice encryptRequestBodyAdvice() {
        return new DecryptRequestBodyAdvice();
    }

}
