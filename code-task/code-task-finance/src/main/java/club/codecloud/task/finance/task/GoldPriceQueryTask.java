package club.codecloud.task.finance.task;

import club.codecloud.task.finance.client.MailMessageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ulei
 * @date 2018/4/12
 */
@Component
public class GoldPriceQueryTask {

    @Autowired
    MailMessageClient mailMessageClient;

    /**
     * 周一到周五，指定时间点，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 0,1,2,9,10,11,13,14,15,20,21,22,23 ? * MON-FRI")
    public void exec() {
        System.out.println(System.currentTimeMillis());
        mailMessageClient.send("mail");
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        System.out.println(System.currentTimeMillis());
        mailMessageClient.send("mail");
    }
}
