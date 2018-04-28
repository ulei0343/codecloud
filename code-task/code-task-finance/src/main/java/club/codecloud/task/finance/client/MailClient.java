package club.codecloud.task.finance.client;

import club.codecloud.message.api.service.MailService;
import club.codecloud.message.api.service.fallback.MailServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author ulei
 * @date 2018/4/27
 */
@FeignClient(value = "message-service", fallback = MailClientFallback.class)
public interface MailClient extends MailService {

}

/**
 * 降级策略
 */
@Component
class MailClientFallback extends MailServiceFallback implements MailClient {
    /*@Override
    public void send(String content) {
        System.out.println("异常降级");
    }*/
}


