package com.yc.study.test;

import java.util.concurrent.*;

/**
 * 测试线程池关闭后再开启队列里的线程是否还会执行
 */
public class TestShutDown {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory factory = (r) -> new Thread(r, "test");
        ExecutorService executorService = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, blockingQueue, factory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 6; i++) {
            executorService.execute(() -> {
                System.out.println("execute sleep thread start");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("execute sleep thread end");
            });

        }

        executorService.execute(() -> System.out.println("execute new thread1..."));
        executorService.shutdown();
        executorService.execute(() -> System.out.println("execute new thread2..."));



    }
}
