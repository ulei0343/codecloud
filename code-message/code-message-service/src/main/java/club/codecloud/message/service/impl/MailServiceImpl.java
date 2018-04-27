package club.codecloud.message.service.impl;

import club.codecloud.message.api.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ulei
 * @date 2018/4/12
 */
@RestController
public class MailServiceImpl implements MailService {
    private final static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private AmqpTemplate rabbitmqTemplate;


    @Override
    public void send(String content) {
        Integer.valueOf("error");
        logger.info("消息队列已接收：" + content);
        rabbitmqTemplate.convertAndSend("mail", content);
    }

}
