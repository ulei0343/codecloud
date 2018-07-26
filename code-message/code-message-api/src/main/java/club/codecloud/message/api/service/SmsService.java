package club.codecloud.message.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface SmsService {

    @RequestMapping(value = "/message/sms/send", method = RequestMethod.GET)
    void send(@RequestParam(value = "content") String content);
}
