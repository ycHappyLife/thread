package com.yc.study.thread.semaphore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest1 {

    public static final int CLIENT_TOTAL = 10;

    public static final int PERMITS = 2;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(PERMITS);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        try {
            for (int i = 0; i < CLIENT_TOTAL; i++) {
                final int count = i;
                executorService.execute(() -> {
                    try {
                        semaphore.acquire(1);
                        resolve(count);
                        semaphore.release(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
        } finally {
            executorService.shutdown();
        }
    }

    private static void resolve(int i) throws InterruptedException {
        System.out.printf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 服务号%d受理中...%n", i);
        Thread.sleep(2000);
    }
}
