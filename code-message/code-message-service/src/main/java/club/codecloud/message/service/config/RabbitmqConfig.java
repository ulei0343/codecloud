package club.codecloud.message.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ulei
 * @date 2018/04/12
 */
@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue mailQueue() {
        return new Queue("mail");
    }
}
