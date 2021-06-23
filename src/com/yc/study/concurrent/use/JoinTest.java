package com.yc.study.concurrent.use;

import java.util.concurrent.TimeUnit;

/**
 * @author ycrag
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        /*Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }*/
        Thread thread = new Thread(new Domino(Thread.currentThread()), "test1");
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate...");
        }
    }
}
