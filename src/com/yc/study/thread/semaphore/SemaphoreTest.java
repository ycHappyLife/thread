package com.yc.study.thread.semaphore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static final int PERMITS_SIZE = 10;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(PERMITS_SIZE);
        MyThread myThread1 = new MyThread("thread1", semaphore);
        MyThread myThread = new MyThread("thread2", semaphore);
        myThread.start();
        myThread1.start();
        int permits = 5;
        try {
            System.out.println(printDate() + Thread.currentThread().getName() + "trying to acquire");
            semaphore.acquire(permits);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(permits);
            System.out.println(printDate() + Thread.currentThread().getName() + " release successfully");
        }
    }

    static class MyThread extends Thread {

        private Semaphore semaphore;

        public MyThread(String name, Semaphore semaphore) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int count = 5;
            try {
                System.out.println(printDate() + Thread.currentThread().getName() + " trying to acquire");
                semaphore.acquire(count);
                System.out.println(printDate() + Thread.currentThread().getName() + " acquire successfully");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(count);
                System.out.println(printDate() + Thread.currentThread().getName() + " release successfully");
            }
        }
    }

    public static String printDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ";
    }
}
