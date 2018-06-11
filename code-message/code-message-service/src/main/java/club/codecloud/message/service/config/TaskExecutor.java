package club.codecloud.message.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ulei
 * @date 2018/4/13
 */
@Configuration
public class TaskExecutor {

    @Autowired
    ThreadPoolConfig threadPoolConfig;

    @Bean(name = "defaultExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(threadPoolConfig.getName());
        executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        executor.setKeepAliveSeconds(threadPoolConfig.getKeepAliveSeconds());
        executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());

        // rejection-policy 拒绝策略
        // 当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
