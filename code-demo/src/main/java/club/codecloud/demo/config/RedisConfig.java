package club.codecloud.demo.config;

import club.codecloud.base.util.cache.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author ulei
 * @date 2018/7/26
 */
@Configuration
public class RedisConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public RedisUtils redisUtils() {
        return new RedisUtils(redisTemplate);
    }
}
