import club.codecloud.base.util.cache.RedisUtils;
import club.codecloud.base.util.number.RandomUtils;
import club.codecloud.demo.DemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ulei
 * @date 2018/7/26
 */
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTest {

    @Autowired
    RedisUtils redisUtils;

    private static final String KEY = "test";

    @Test
    public void setTest() {
        String value = RandomUtils.randomStringFixLength(8);
        log.info("set:{}", value);
        redisUtils.set(KEY, value);
        String getValue = redisUtils.get(KEY);
        log.info("get:{}", getValue);
    }

}
