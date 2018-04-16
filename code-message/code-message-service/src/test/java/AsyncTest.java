import club.codecloud.message.service.MessageServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ulei
 * @date 2018/4/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageServiceApplication.class)
public class AsyncTest {

    @Test
    public void async() {
        for (int i = 0; i < 100; i++) {
            print(i);
        }
    }

    @Async
    public void print(int i){
        System.out.println(Thread.currentThread().getName());
        System.out.println(i);

    }
}
