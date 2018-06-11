import club.codecloud.message.service.MessageServiceApplication;
import club.codecloud.message.service.impl.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ulei
 * @date 2018/4/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageServiceApplication.class)
public class MailTest {

    @Autowired
    MailClient mailClient;

    @Test
    public void sendSimple() throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            mailClient.sendSimpleMail("hull@codecloud.club", String.valueOf(i), String.valueOf(System.currentTimeMillis()));
        }
        System.out.println("发送完毕");
        Thread.sleep(5000);
    }

}
