package club.codecloud.message.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ulei
 * @date 2018/4/12
 */
public interface MailService {

    @RequestMapping(value = "/message/email/send", method = RequestMethod.GET)
    void send(@RequestParam(value = "content") String content);


}


