package club.codecloud.job.client;

import club.codecloud.message.api.service.SmsService;
import club.codecloud.job.client.fallback.SmsFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "message-service", fallback = SmsFeignFallback.class)
public interface SmsFeign extends SmsService {
}
