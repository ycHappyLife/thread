package com.yc.study.concurrent.use;

import com.yc.study.concurrent.util.SleepUtils;
import jdk.nashorn.internal.ir.Block;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ycrag
 */
public class WaitNotifyTest {

    static Object lock = new Object();

    static boolean flag = true;

    public static void main(String[] args) {
        Wait wait = new Wait();
        Thread waitThread = new Thread(wait, "WaitThread");
        Notify notify = new Notify();
        Thread notifyThread = new Thread(notify, "NotifyThread");
        waitThread.start();
        SleepUtils.second(1);
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false. running @"
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock. notify @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            System.out.println(Thread.currentThread() + " hold lock again. sleep @ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            SleepUtils.second(5);
        }
    }
}
