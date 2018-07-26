package club.codecloud.message.api.service.fallback;

import club.codecloud.message.api.service.SmsService;

public class SmsServiceFallback implements SmsService {

    @Override
    public void send(String content) {
        System.out.println("触发fallback");
        System.out.println(content);
    }
}
