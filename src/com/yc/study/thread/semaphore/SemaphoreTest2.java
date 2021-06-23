package com.yc.study.thread.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest2 {
/*
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        System.out.println("try to acquire...");
        try {
            boolean b = semaphore.tryAcquire(8);
            System.out.println(b);
            System.out.println("acquire success...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(8);
            System.out.println("release...");
        }

    }*/


    /**
     * 排队总人数（请求总数）
     */
    public static final int CLIENT_TOTAL = 10;

    /**
     * 可同时受理业务的窗口数量（同时并发执行的线程数）
     */
    public static final int THREAD_TOTAL = 2;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws Exception {
        // 再次强调最好使用ThreadPoolExecutor创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
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
        System.out.printf(sdf.format(new Date()) + " 服务号%d，受理业务中。。。%n", i);
        Thread.sleep(2000);
    }
}
