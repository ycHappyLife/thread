package com.yc.study.thread.linkedblockqueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LinkedBlockingQueueDemo {

    private final static AtomicInteger PRODUCE_COUNT = new AtomicInteger(0);

    private final static AtomicInteger CONSUMER_COUNT = new AtomicInteger(0);

    private static LinkedBlockingQueue<Integer> linkedBlockingDeque = new LinkedBlockingQueue<>(3);

    public static final int COUNT = 100;

    static class Producer implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 0; i < COUNT; i++) {
                    Integer messageId = PRODUCE_COUNT.incrementAndGet();
                    linkedBlockingDeque.put(messageId);
                    System.out.printf("%s生产消息id=%s,剩余容量%s%n", Thread.currentThread().getName(), messageId, linkedBlockingDeque.remainingCapacity());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (CONSUMER_COUNT.get() < COUNT) {
                try {
                    Integer messageId = linkedBlockingDeque.poll(5, TimeUnit.SECONDS);
                    System.out.printf("%s消费消息id=%s,剩余容量%s%n", Thread.currentThread().getName(), messageId, linkedBlockingDeque.remainingCapacity());
                    CONSUMER_COUNT.getAndIncrement();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<Integer> linkedBlockingDeque = new LinkedBlockingQueue<>(3);
        linkedBlockingDeque.put(1);
        linkedBlockingDeque.take();
        System.out.println("come in ");
//        new Thread(new Producer(), "生产者1").start();
        /*new Thread(new Consumer(), "消费者1").start();
        new Thread(new Consumer(), "消费者2").start();
        new Thread(new Consumer(), "消费者3").start();*/

    }
}
