package com.scrapy.common;

import com.scrapy.common.log.Log;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ThreadPool {

    private ExecutorService executor;
    private final int fixedPoolSize = 3;

    private final BlockingQueue<Callable> interruptedTaskQueue = new LinkedBlockingQueue<>();

    public ThreadPool() {
        initialize();
    }

    public void initialize() {
        if (executor == null) {
            Log.info("ThreadPool create...");
            this.executor = Executors.newFixedThreadPool(fixedPoolSize);
        }
    }

    public void executeTask(BlockingQueue<Callable> tasks) {

        if (executor == null) {
            Log.error("ThreadPool executeTask : not exist");
            return;
        }

        int completedTasks = 0;
        for (Callable task : tasks) {
            try {
                Future<String> future = executor.submit(task);
                Thread.sleep(3000);
                completedTasks++;

            } catch (InterruptedException e) {
                //Thread.currentThread().interrupt();
                interruptedTaskQueue.offer(task);
                Log.error("ThreadPool executeTask : InterruptedException, e : {}", e.getMessage());
            }
        }

        // interrupted 재처리.
        if (tasks.size() != completedTasks) {
            executeTaskInterrupted();
        }

        try {
            if (executor.awaitTermination(120, TimeUnit.SECONDS)) {
                Log.info("ThreadPool executeTask : Complete...", this.interruptedTaskQueue.size());

            } else {
                executor.shutdownNow();
                Log.error("ThreadPool executeTask : shutdownNow...");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Log.error("ThreadPool executeTask : exception occued");
        }

        Log.info("ThreadPool total task : {}, completed task : {}", tasks.size(), completedTasks);
    }

    private void executeTaskInterrupted() {
        if (interruptedTaskQueue.size() > 0) {
            for (Callable task : interruptedTaskQueue) {
                try {
                    Future<String> future = executor.submit(task);
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Log.error("ThreadPool executeTaskInterrupted : interruptedException, e : {}", e.getMessage());
                }
            }
        }

        try {
            if (executor.awaitTermination(120, TimeUnit.SECONDS)) {
                Log.info("ThreadPool executeTaskInterrupted({}) : Complete...", this.interruptedTaskQueue.size());
                interruptedTaskQueue.clear();

            } else {
                executor.shutdownNow();
                Log.error("ThreadPool executeTaskInterrupted : shutdownNow...");
            }

        } catch (InterruptedException e) {
            executor.shutdownNow();
            Log.error("ThreadPool executeTaskInterrupted : exception occued");
            // 메시지 발송 추가 필요
        }
    }

    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            Log.info("ThreadPool shutdown...");
            executor.shutdown();
        }
    }
}
