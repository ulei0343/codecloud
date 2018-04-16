package club.codecloud.message.service;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ulei
 */
@EnableDiscoveryClient
@EnableAsync
@SpringBootApplication
public class MessageServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MessageServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
