package com.yc.study.thread.interrupt;

public class IsInterruptedTest extends Thread {

    public static void main(String[] args) throws InterruptedException {
        IsInterruptedTest isInterruptedTest = new IsInterruptedTest();
        System.out.println("1:" + isInterruptedTest.isInterrupted());
        isInterruptedTest.start();
        Thread.sleep(3000);
        isInterruptedTest.interrupt();
        Thread.sleep(3000);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("service is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                System.out.println("3:" + Thread.currentThread().isInterrupted());
            }

        }
        System.out.println("4:" + Thread.currentThread().isInterrupted());
    }
}
