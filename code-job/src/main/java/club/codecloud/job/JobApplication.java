package club.codecloud.job;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ulei
 * @date 2018/4/12
 */
@SpringCloudApplication
@EnableScheduling
@EnableFeignClients(basePackages = "club.codecloud.job.client")
@ComponentScan(basePackages = "club.codecloud")
@EnableHystrixDashboard
public class JobApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(JobApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
