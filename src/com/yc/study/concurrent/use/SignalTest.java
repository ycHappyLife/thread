package com.yc.study.concurrent.use;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ycrag
 */
public class SignalTest {

    private static AtomicInteger signal = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread ta = new Thread(new ThreadA());
        Thread tb = new Thread(new ThreadB());
        ta.start();
        tb.start();
    }

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            while (signal.intValue() < 5) {
                if (signal.intValue() % 2 == 0) {
                    System.out.println("ThreadA: " + signal.intValue());
                    signal.getAndIncrement();
                }
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            while (signal.intValue() < 5) {
                if (signal.intValue() % 2 == 1) {
                    System.out.println("ThreadB: " + signal.intValue());
                    signal.getAndIncrement();
                }
            }
        }
    }
}
