package club.codecloud.base.config.exception;

import club.codecloud.base.constant.ResultCode;
import club.codecloud.base.util.base.JsonUtils;
import club.codecloud.base.util.base.Result;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleException(HttpServletRequest request, Exception e) {
        logger.error(request.getRequestURI(), e);
        return Result.error(ResultCode.DEFAULT_ERROR);
    }


    /**
     * 处理参数校验的异常信息
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBindException(HttpServletRequest request, MethodArgumentNotValidException e) {
        List<NotValidMessage> notValidMessageList = e.getBindingResult().getFieldErrors().stream().map(
                fieldError -> new NotValidMessage(fieldError.getField(), fieldError.getDefaultMessage())
        ).collect(Collectors.toList());
        logger.error("【{}】参数错误：{}", request.getRequestURI(), JSON.toJSONString(notValidMessageList));
        return Result.error(ResultCode.PARAMETER_ERROR, JsonUtils.toJson(notValidMessageList));
    }


    private class NotValidMessage {
        private String filed;
        private String message;

        public String getFiled() {
            return filed;
        }

        public String getMessage() {
            return message;
        }

        private NotValidMessage(String filed, String message) {
            this.filed = filed;
            this.message = message;
        }
        
    }

}
