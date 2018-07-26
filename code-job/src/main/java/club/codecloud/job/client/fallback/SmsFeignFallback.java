package club.codecloud.job.client.fallback;

import club.codecloud.message.api.service.fallback.SmsServiceFallback;
import club.codecloud.job.client.SmsFeign;
import org.springframework.stereotype.Component;

@Component
public class SmsFeignFallback extends SmsServiceFallback implements SmsFeign {
}
