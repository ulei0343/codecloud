package club.codecloud.message.service.impl;

import club.codecloud.message.service.config.MailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 邮件发送客户端
 *
 * @author ulei
 * @date 2018/4/27
 */
@Component
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     *
     * @param to      收件人地址
     * @param title   邮件标题
     * @param content 邮件内容
     */
    @Async("defaultExecutor")
    public void sendSimpleMail(String to, String title, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailConfig.getFrom());
            message.setTo(to);
            message.setSubject(title);
            message.setText(content);
            mailSender.send(message);
            logger.info("send mail to [{}] success", to);
        } catch (MailException e) {
            logger.error("send mail to [{}] error", to, e);
        }
    }
}
