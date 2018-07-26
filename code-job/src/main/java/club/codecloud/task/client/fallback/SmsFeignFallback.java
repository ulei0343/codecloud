package club.codecloud.task.client.fallback;

import club.codecloud.message.api.service.fallback.SmsServiceFallback;
import club.codecloud.task.client.SmsFeign;
import org.springframework.stereotype.Component;

@Component
public class SmsFeignFallback extends SmsServiceFallback implements SmsFeign {
}
