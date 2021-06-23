package com.yc.study.concurrent.use;

import com.yc.study.concurrent.util.SleepUtils;

/**
 * @author ycrag
 */
public class SafeShutDown {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        t1.start();
        SleepUtils.second(1);
        t1.interrupt();

        Runner runner = new Runner();
        Thread t2 = new Thread(runner);
        t2.start();
        SleepUtils.second(1);
        runner.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }
        public void cancel() {
            this.on = false;
        }
    }


}
