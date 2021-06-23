package com.yc.study.thread.delayqueue;

import com.yc.study.thread.cyclicbarrier.CyclicBarrierTest;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

    private static DelayQueue<Message> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        Message item1 = new Message("消息1", 5, TimeUnit.SECONDS);
        Message item2 = new Message("消息2", 10, TimeUnit.SECONDS);
        Message item3 = new Message("消息3", 15, TimeUnit.SECONDS);
        Thread t1 = new Thread(new DelayQueueDemo().new DaemonThread(), "t1");
        Thread t2 = new Thread(new DelayQueueDemo().new DaemonThread(), "t2");
        Thread t3 = new Thread(new DelayQueueDemo().new DaemonThread(), "t3");
        t1.start();
        t2.start();
        t3.start();

        delayQueue.put(item1);
        delayQueue.put(item2);
        delayQueue.put(item3);

    }

    public class DaemonThread implements Runnable {

        @Override
        public void run() {
            System.out.println(CyclicBarrierTest.printDate() + Thread.currentThread().getName() + " 开始");
            try {
                for (;;) {
                    Message massage = delayQueue.take();
                    System.out.println(CyclicBarrierTest.printDate() + Thread.currentThread().getName() + " 消息出队，属性name=" + massage.name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(CyclicBarrierTest.printDate() + Thread.currentThread().getName() + " 结束");
        }
    }
}
