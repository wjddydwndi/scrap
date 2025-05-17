package com.scrapy.common;

import okhttp3.Call;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {

    private final BlockingQueue<Callable> taskQueue = new LinkedBlockingQueue<>();

    public void add(Callable task) {
        taskQueue.offer(task);
    }

    public void add(Callable ... tasks) {
        for (int i = 0; i < tasks.length; i++) {
            taskQueue.offer(tasks[i]);
        }
    }

    public BlockingQueue<Callable> get() {
        return taskQueue;
    }

    public BlockingQueue<Callable> drainTo() {
        BlockingQueue<Callable> drainedTasks = new LinkedBlockingQueue<>();
        taskQueue.drainTo(drainedTasks);
        return drainedTasks;
    }
}
