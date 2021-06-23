package com.yc.study.thread.sychronizeduse;

public class SynchronizeTest1 implements Runnable {

    public static synchronized void increase() {
        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName() + " execute:" + i);
        }
    }

    @Override
    public void run() {
        increase();
    }

    public static void main(String[] args) throws InterruptedException {
//        SynchronizeTest1 test1 =  new SynchronizeTest1();
        Thread thread1 = new Thread(new SynchronizeTest1(), "thread1");
        Thread thread2 = new Thread(new SynchronizeTest1(), "thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("end");
    }
}
