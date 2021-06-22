package com.yc.study.concurrent.aqs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    private final Object[] items = new Object[100];

    private int inputIndex;

    private int outputIndex;

    private int count;

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> {
                    for (int j = 0; j < 10; j++) {
                        try {
                            Thread.sleep(10);
                            producerConsumer.put(String.format("生产者%s\t于%s生产的数据%d", Thread.currentThread().getName(), printDate(), j));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            executorService.submit(() ->  {
                for (int i = 0; i < 100; i++) {
                    try {
                        Object output = producerConsumer.take();
                        System.out.printf("消费者消费%s\n", output);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } finally {
            assert executorService != null;
            executorService.shutdown();
        }
    }

    public void put(Object input) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[inputIndex] = input;
            if (++inputIndex == items.length) {
                inputIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object item = items[outputIndex];
            if (++outputIndex == items.length) {
                outputIndex = 0;
            }
            --count;
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
    /**
     * 日期格式化
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String printDate() {
        return simpleDateFormat.format(new Date());
    }
}
