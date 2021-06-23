package com.yc.study.thread.cyclicbarrier;

public class TourGuideTask implements Runnable {
    @Override
    public void run() {
        System.out.println(CyclicBarrierTest.printDate() +"*****导游发放护照*****");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
