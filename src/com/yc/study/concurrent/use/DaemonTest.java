package com.yc.study.concurrent.use;

import com.yc.study.concurrent.util.SleepUtils;

/**
 * this class test the DaemonThread will shutdown
 * with the main thread terminate
 * @author ycrag
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new DaemonRunner(), "DaemonRunner");
        t1.setDaemon(true);
        t1.start();
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }


}
