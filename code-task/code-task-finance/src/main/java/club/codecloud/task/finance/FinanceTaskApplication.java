package club.codecloud.task.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ulei
 * @date 2018/4/12
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FinanceTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceTaskApplication.class, args);
    }
}