package com.fantasy.rivendell.service;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lingyao on 16/7/14.
 */

@Component
public class AsynExecutorManager {
    public static int corePoolSize = 2;
    public static int maximumPoolSize = 100;
    public static long keepAliveTime = 60;
    public static int blockingQueueCapacity = 3000;
    private static Log logger = LogFactory.getLog(AsynExecutorManager.class);
    private static ThreadPoolExecutor innerPoll;

    public void execute(Runnable command) {
        try {
            innerPoll.submit(command);
        } catch (RejectedExecutionException e) {
            logger.error("AsynExecutorUtil-Pool is full...", e);
        }
    }

    public String getWorkStatus() {
        return innerPoll.toString();
    }

    @PostConstruct
    public void init() {
        //初始化
        innerPoll = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(blockingQueueCapacity),
                new DefaultThreadFactory("AsynExecutorUtil-Pool"));
        //预热
        for (int i = 0; i < corePoolSize; i++) {
            innerPoll.execute(() -> {
            });
        }
    }
}
