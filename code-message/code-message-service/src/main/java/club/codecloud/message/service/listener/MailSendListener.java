package club.codecloud.message.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ulei
 * @date 2018/4/12
 */
@Component
@RabbitListener(queues = "mail")
public class MailSendListener {
    private final static Logger logger = LoggerFactory.getLogger(MailSendListener.class);

    @RabbitHandler
    public void sendMail(String content) {

        logger.info("接收到邮件：{}", content);

        logger.info("发送邮件");
    }
}
