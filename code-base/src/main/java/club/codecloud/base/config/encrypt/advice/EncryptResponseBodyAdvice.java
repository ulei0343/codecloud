package club.codecloud.base.config.encrypt.advice;

import club.codecloud.base.config.encrypt.EncryptProperties;
import club.codecloud.base.config.encrypt.annotation.Encrypt;
import club.codecloud.base.util.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 加密返回信息
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getAnnotatedElement().isAnnotationPresent(Encrypt.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!encryptProperties.isDebug()) {
            return AESUtils.encrypt(body.toString(), encryptProperties.getKey());
        }
        return body;
    }
}
