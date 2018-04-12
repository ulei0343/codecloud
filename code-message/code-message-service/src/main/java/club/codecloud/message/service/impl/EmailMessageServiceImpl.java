package club.codecloud.message.service.impl;

import club.codecloud.message.api.service.EmailMessageService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ulei
 * @date 2018/4/12
 */
@RestController
public class EmailMessageServiceImpl implements EmailMessageService {
    @Override
    public void send(String content) {
        System.out.println(content);
    }
}
