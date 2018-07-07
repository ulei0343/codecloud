package club.codecloud.demo.common.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ulei
 * @date 2018/4/27
 */
@Component
public class MailConfig {

    @Value("${spring.mail.username}")
    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
