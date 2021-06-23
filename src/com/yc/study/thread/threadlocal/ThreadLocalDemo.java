package com.yc.study.thread.threadlocal;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadLocalDemo {

    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 3);

    private Semaphore semaphore = new Semaphore(1);

    private Random random = new Random();

    public class Worker implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1000));
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int i = threadLocal.get();
            System.out.println("初始值：" + i);
            int newValue = random.nextInt();
            threadLocal.set(newValue);
            System.out.println("新值：" + newValue);
            System.out.println("threadLocal 值：" + threadLocal.get());
            semaphore.release();
            threadLocal.remove();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        executorService.execute(threadLocalDemo.new Worker());
        executorService.execute(threadLocalDemo.new Worker());
        executorService.execute(threadLocalDemo.new Worker());
        executorService.shutdown();
    }
}
