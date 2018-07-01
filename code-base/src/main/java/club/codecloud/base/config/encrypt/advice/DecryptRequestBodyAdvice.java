package club.codecloud.base.config.encrypt.advice;

import club.codecloud.base.config.encrypt.EncryptProperties;
import club.codecloud.base.config.encrypt.annotation.Decrypt;
import club.codecloud.base.util.AESUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 解密请求体里的内容
 */
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    private EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getAnnotatedElement().isAnnotationPresent(Decrypt.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        if (!encryptProperties.isDebug()) {
            return new DecryptHttpInputMessage(inputMessage.getBody(), inputMessage.getHeaders());
        }

        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    private class DecryptHttpInputMessage implements HttpInputMessage {

        private final InputStream body;
        private final HttpHeaders headers;

        private DecryptHttpInputMessage(InputStream body, HttpHeaders headers) throws IOException {
            String data = IOUtils.toString(body, StandardCharsets.UTF_8);
            // AES解密数据
            String decryptData = AESUtils.decrypt(data, encryptProperties.getKey());
            this.body = IOUtils.toInputStream(decryptData, StandardCharsets.UTF_8);
            this.headers = headers;
        }

        @Override
        public InputStream getBody() throws IOException {
            return this.body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }
    }
}
