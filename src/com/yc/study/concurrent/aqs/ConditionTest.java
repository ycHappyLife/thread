package com.yc.study.concurrent.aqs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    /**
     * 获取独占锁
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 获取条件1
     */
    private Condition condition1 = lock.newCondition();

    /**
     * 获取条件2
     */
    private Condition condition2 = lock.newCondition();

    /**
     * 获取条件3
     */
    private Condition condition3 = lock.newCondition();


    /**
     * 日期格式化
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Thread1 thread1 = conditionTest.new Thread1();
        Thread2 thread2 = conditionTest.new Thread2();
        Thread3 thread3 = conditionTest.new Thread3();
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
        Thread.sleep(2000);
        singleTest(conditionTest);
        executorService.shutdown();
    }

    public class Thread1 implements Runnable {

        public Thread1() {
        }

        @Override
        public void run() {
            try {
                Thread.currentThread().setName(Thread1.class.getSimpleName());
                System.out.printf("%s线程启动\n", Thread.currentThread().getName());
                lock.lock();
                condition1.await();
                System.out.printf("%s线程被唤醒", Thread.currentThread().getName());
                printDate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public class Thread2 implements Runnable {

        public Thread2() {
        }

        @Override
        public void run() {
            try {
                Thread.currentThread().setName(Thread2.class.getSimpleName());
                System.out.printf("%s线程启动\n", Thread.currentThread().getName());
                lock.lock();
                condition2.await();
                System.out.printf("%s线程被唤醒", Thread.currentThread().getName());
                printDate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public class Thread3 implements Runnable {

        public Thread3() {
        }

        @Override
        public void run() {
            try {
                Thread.currentThread().setName(Thread3.class.getSimpleName());
                System.out.printf("%s线程启动\n", Thread.currentThread().getName());
                lock.lock();
                condition3.await();
                System.out.printf("%s线程被唤醒", Thread.currentThread().getName());
                printDate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void printDate() {
        System.out.println(simpleDateFormat.format(new Date()));
    }

    public static void singleTest(ConditionTest conditionTest) throws InterruptedException {
        conditionTest.lock.lock();
        conditionTest.condition1.signal();
        conditionTest.lock.unlock();
        Thread.sleep(2000);
        conditionTest.lock.lock();
        conditionTest.condition2.signal();
        conditionTest.lock.unlock();
        Thread.sleep(2000);
        conditionTest.lock.lock();
        conditionTest.condition3.signal();
        conditionTest.lock.unlock();
    }
}
