package club.codecloud.demo;

import club.codecloud.base.config.encrypt.annotation.EnableEncrypt;
import club.codecloud.base.config.exception.EnableExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ulei
 */
@EnableEncrypt
@EnableExceptionHandler
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
