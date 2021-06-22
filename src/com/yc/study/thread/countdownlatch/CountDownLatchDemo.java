package com.yc.study.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new QueueTask(countDownLatch));
        executorService.execute(new DoctorTask(countDownLatch));
        try {
            countDownLatch.await();
            System.out.println("结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
