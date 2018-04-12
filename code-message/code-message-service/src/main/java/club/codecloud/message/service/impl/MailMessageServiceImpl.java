package club.codecloud.message.service.impl;

import club.codecloud.message.api.service.MailMessageService;
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
public class MailMessageServiceImpl implements MailMessageService {
    private final static Logger logger = LoggerFactory.getLogger(MailMessageServiceImpl.class);
    @Autowired
private AmqpTemplate rabbitMQTemplate;


    @Override
    public void send(String content) {
        logger.info(content);
        logger.info("已发送给消息队列");
        rabbitMQTemplate.convertAndSend("mail", content);
    }
}
