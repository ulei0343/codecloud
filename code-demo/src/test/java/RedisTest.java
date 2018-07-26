import club.codecloud.base.util.cache.RedisUtils;
import club.codecloud.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ulei
 * @date 2018/7/26
 */
@SpringBootTest(classes = {DemoApplication.class})
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void setTest(){
        String key = "test";
        redisUtils.set(key,"test");
        System.out.println(redisUtils.get(key));
    }
}
