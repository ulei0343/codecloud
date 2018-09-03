package club.codecloud.demo;

import club.codecloud.base.config.encrypt.annotation.EnableEncrypt;
import club.codecloud.base.config.exception.EnableExceptionHandler;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ulei
 */
@EnableEncrypt
@EnableExceptionHandler
@SpringBootApplication
@MapperScan(basePackages={"club.codecloud.demo.dao"})
@EnableMethodCache(basePackages = {"club.codecloud.demo"})
@EnableCreateCacheAnnotation
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
