package com.yc.study.thread.cyclicbarrier;

import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TravelTask implements Runnable {

    private CyclicBarrier cyclicBarrier;

    private String name;

    private int arriveTime;

    public TravelTask(CyclicBarrier cyclicBarrier, String name, int arriveTime) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
        this.arriveTime = arriveTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(arriveTime * 1000);
            System.out.println(CyclicBarrierTest.printDate() + " " + name + "到达集合点");
            cyclicBarrier.await();
            System.out.println(CyclicBarrierTest.printDate() + " " + name + "开始旅行啦~~~");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
