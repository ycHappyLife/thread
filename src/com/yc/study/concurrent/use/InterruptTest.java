package com.yc.study.concurrent.use;

import com.yc.study.concurrent.util.SleepUtils;

/**
 * This Class is test the mean of interrupt method
 * and the interrupted method details
 * @author yc
 */
public class InterruptTest {

    public static void main(String[] args) {
        Thread sleepThread = new Thread(() -> {
            while (true) {
                SleepUtils.second(10);
            }
        }, "SleepThread");
        Thread busyThread = new Thread(() -> {
            while (true) {
            }
        }, "BusyThread");
        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        SleepUtils.second(5);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is:" + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is:" + busyThread.isInterrupted());

        SleepUtils.second(2);
    }
}
