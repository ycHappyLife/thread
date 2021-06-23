package com.yc.study.thread.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {

    public static void main(String[] args) {

        int corePoolSize = 2; //核心线程数

        int maxPoolSize = 4; //线程池最大数

        long keepAliveTime = 10; //非核心线程闲置超时时长

        TimeUnit unit = TimeUnit.SECONDS; //时间单位

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2); // 阻塞队列

        ThreadFactory threadFactory = new NameThreadFactory(); //线程工厂

        RejectedExecutionHandler rejectedExecutionHandler = new MyIgnorePolicy(); //拒绝策略

        ThreadPoolExecutor threadPoolExecutor = null;

        try {
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, threadFactory, rejectedExecutionHandler);
            threadPoolExecutor.prestartAllCoreThreads();
            int count = 10;
            for (int i = 1; i <= count; i++) {
                RunnableTask runnableTask = new RunnableTask(String.valueOf(i));
                threadPoolExecutor.submit(runnableTask);
            }
        } finally {
            assert threadPoolExecutor != null;
            threadPoolExecutor.shutdown();
        }

    }

    static class NameThreadFactory implements ThreadFactory {

        private final AtomicInteger atomicInteger = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread (r, "线程-" + atomicInteger.getAndIncrement());
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r, executor);
        }

        private void doLog(Runnable runnable, ThreadPoolExecutor executor) {
            System.err.println(runnable.toString() + " rejected");
        }
    }

    static class RunnableTask implements Runnable {

        private String name;

        public RunnableTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + "is running");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "RunnableTask [name=" + name + "]";
        }
    }
}
