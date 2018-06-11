package club.codecloud.message.api.service.fallback;

import club.codecloud.message.api.service.MailService;

/**
 * @author ulei
 * @date 2018/4/24
 */
public class MailServiceFallback implements MailService {
    @Override
    public void send(String content) {
        System.out.println("触发fallback");
        System.out.println(content);
    }
}
