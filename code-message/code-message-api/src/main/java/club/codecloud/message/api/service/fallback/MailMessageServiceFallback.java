package club.codecloud.message.api.service.fallback;

import club.codecloud.message.api.service.MailMessageService;
import org.springframework.stereotype.Component;

/**
 * @author ulei
 * @date 2018/4/24
 */
@Component
public class MailMessageServiceFallback implements MailMessageService {
    @Override
    public void send(String content) {
        System.out.println("触发fallback");
        System.out.println(content);
    }
}
