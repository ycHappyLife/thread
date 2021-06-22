package com.yc.study.concurrent.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AqsConditionTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition1 = lock.newCondition();
        WaiterThread waiterThread = new WaiterThread(lock, condition);
        SignalThread signalThread = new SignalThread(lock, condition1);
        Thread t1 = new Thread(waiterThread);
        Thread t2 = new Thread(signalThread);
        t1.start();
        t2.start();
    }

    static class WaiterThread implements Runnable {

        private ReentrantLock lock;

        private Condition condition;

        public WaiterThread(ReentrantLock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("begin wait");
                condition.await();
                System.out.println("end wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class SignalThread implements Runnable {

        private ReentrantLock lock;

        private Condition condition;

        public SignalThread(ReentrantLock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("begin signal");
                condition.signal();
                System.out.println("end signal");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
