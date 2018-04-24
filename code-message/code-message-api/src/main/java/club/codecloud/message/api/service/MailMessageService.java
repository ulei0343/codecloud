package club.codecloud.message.api.service;

import club.codecloud.message.api.service.fallback.MailMessageServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ulei
 * @date 2018/4/12
 */
@FeignClient(value = "MESSAGE-SERVICE", fallback = MailMessageServiceFallback.class)
//@RequestMapping("/message/email")
public interface MailMessageService {

    @RequestMapping(value = "/message/email/send", method = RequestMethod.GET)
    void send(@RequestParam(value = "content") String content);


}


