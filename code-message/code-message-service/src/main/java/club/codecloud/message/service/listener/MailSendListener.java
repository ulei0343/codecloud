package club.codecloud.message.service.listener;

import club.codecloud.message.service.impl.MailClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ulei
 * @date 2018/4/12
 */
@Component
@RabbitListener(queues = "mail")
public class MailSendListener {
    private final static Logger logger = LoggerFactory.getLogger(MailSendListener.class);

    @Autowired
    MailClient mailClient;

    @RabbitHandler
    public void sendMail(String content) {
        mailClient.sendSimpleMail("hull@codecloud.club", "", "");
    }
}
