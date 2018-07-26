package club.codecloud.task.client;

import club.codecloud.message.api.service.SmsService;
import club.codecloud.task.client.fallback.SmsClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "message-service", fallback = SmsClientFallback.class)
public interface SmsClient extends SmsService {
}
