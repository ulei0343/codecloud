package club.codecloud.base.config.exception;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用异常处理
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(GlobalExceptionHandler.class)
public @interface EnableExceptionHandler {

}
