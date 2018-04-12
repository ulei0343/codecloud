package club.codecloud.task.finance.service;

import club.codecloud.message.api.service.EmailMessageService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author ulei
 * @date 2018/4/12
 */
@FeignClient(value = "MESSAGE-SERVICE")
public interface EmailMessageFacade extends EmailMessageService {
}
