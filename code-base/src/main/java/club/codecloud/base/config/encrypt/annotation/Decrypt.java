package club.codecloud.base.config.encrypt.annotation;

import java.lang.annotation.*;

/**
 * 解密注解
 * 
 * <p>加了此注解的接口将进行数据解密操作<p>
 * 
 * @author yinjihuan
 * 
 * @about http://cxytiandi.com/about
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

}
