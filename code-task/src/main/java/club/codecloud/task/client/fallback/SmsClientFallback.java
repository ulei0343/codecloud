package club.codecloud.task.client.fallback;

import club.codecloud.message.api.service.fallback.SmsServiceFallback;
import club.codecloud.task.client.SmsClient;
import org.springframework.stereotype.Component;

@Component
public class SmsClientFallback extends SmsServiceFallback implements SmsClient {
}
