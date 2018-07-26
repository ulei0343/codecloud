package club.codecloud.task.client;

import club.codecloud.message.api.service.MailService;
import club.codecloud.task.client.fallback.MailClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ulei
 * @date 2018/4/27
 */
@FeignClient(value = "message-service", fallback = MailClientFallback.class)
public interface MailClient extends MailService {

}




