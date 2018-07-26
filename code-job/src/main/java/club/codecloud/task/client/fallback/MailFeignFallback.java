package club.codecloud.task.client.fallback;

/**
 * 降级策略
 *
 * @author ulei
 * @date 2018/4/28
 */

import club.codecloud.message.api.service.fallback.MailServiceFallback;
import club.codecloud.task.client.MailFeign;
import org.springframework.stereotype.Component;

@Component
public class MailFeignFallback extends MailServiceFallback implements MailFeign {
    @Override
    public void send(String content) {
        System.out.println("异常降级");
    }
}