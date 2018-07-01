package club.codecloud.base.config.encrypt.annotation;

import club.codecloud.base.config.encrypt.EncryptAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用加密
 * <p>在Spring Boot启动类上加上此注解<p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EncryptAutoConfiguration.class})
public @interface EnableEncrypt {

}
