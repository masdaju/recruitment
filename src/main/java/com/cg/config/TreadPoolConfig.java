package com.cg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
@Configuration
public class TreadPoolConfig {
@Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() + 1);
        //最大线程数
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2 + 1);
        //队列容量
        executor.setQueueCapacity(1000000);
        executor.setRejectedExecutionHandler((r, executor1) ->{
            //自定义拒绝策略
                System.out.println("rejectedExecution");
        });
//
        executor.initialize();
        return executor;
    }

}