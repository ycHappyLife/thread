package com.yc.study.thread.aqs.share;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private final static Semaphore semaphore = new Semaphore(3);

    private final static int permits = 1;

    static class TestThread implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire(permits);
                System.out.println(LocalDateTime.now() + Thread.currentThread().getName() + "进来了");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(permits);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new SemaphoreDemo.TestThread(), "t1");
        Thread t2 = new Thread(new SemaphoreDemo.TestThread(), "t2");
        Thread t3 = new Thread(new SemaphoreDemo.TestThread(), "t3");
        Thread t4 = new Thread(new SemaphoreDemo.TestThread(), "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
