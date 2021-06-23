package com.yc.study.concurrent.aqs;

import java.time.LocalTime;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) {
        System.out.println("main thread start");
        System.out.println("main thread interrupt status: " + Thread.currentThread().isInterrupted());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("come in Runnable");
                System.out.println(LocalTime.now() + "t1 thread interrupt status: " + Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        System.out.println(LocalTime.now() + "t1 thread interrupt status: " + t1.isInterrupted());
        //LockSupport.unpark(t1);

    }


}
