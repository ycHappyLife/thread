package com.yc.study.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class DoctorTask implements Runnable {

    private CountDownLatch countDownLatch;

    public DoctorTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始看医生...");
            Thread.sleep(5000);
            System.out.println("看医生结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
