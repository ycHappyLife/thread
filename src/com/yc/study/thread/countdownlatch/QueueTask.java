package com.yc.study.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class QueueTask implements Runnable {

    private CountDownLatch countDownLatch;

    public QueueTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始排队...");
            Thread.sleep(10000);
            System.out.println("排队完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
