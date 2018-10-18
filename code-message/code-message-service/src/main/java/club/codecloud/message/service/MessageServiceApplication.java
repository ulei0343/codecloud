package club.codecloud.message.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ulei
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@MapperScan(basePackages={"club.codecloud.message.service.dao"})
public class MessageServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MessageServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
