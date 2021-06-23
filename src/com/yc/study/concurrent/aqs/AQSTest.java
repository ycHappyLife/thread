package com.yc.study.concurrent.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ycrag
 */
public class AQSTest {

    private static int s = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        AqsThread aqsThread = new AqsThread(lock);
        Thread thread = new Thread(aqsThread, "thread" + 1);
        AqsThread aqsThread1 = new AqsThread(lock);
        Thread thread1 = new Thread(aqsThread, "thread" + 2);
        AqsThread aqsThread3 = new AqsThread(lock);
        Thread thread3 = new Thread(aqsThread, "thread" + 3);
        thread.start();
        thread1.start();
        thread3.start();
    }

    static int test(int i) {
        if (i < 0 || i > 10) {
            return i;
        }
        for (;;) {
            i++;
            if(i == 10) {
                return i;
            }
        }
    }

    static class AqsThread implements Runnable {

        private ReentrantLock lock;

        public AqsThread() {
        }

        public AqsThread(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                s++;
                System.out.println("currentThread: " + Thread.currentThread().getName() + "value: " + s);
            }catch (Exception e) {
                System.out.println("aaa");
            } finally {
                lock.unlock();
            }
        }
    }

}
