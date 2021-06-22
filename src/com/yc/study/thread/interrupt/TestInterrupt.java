package com.yc.study.thread.interrupt;

import com.yc.study.concurrent.use.ThreadDemo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.LockSupport;

public class TestInterrupt {

    public static void main(String[] args) throws Exception {

        TestInterrupt testInterrupt = new TestInterrupt();
        Thread1 t1 = testInterrupt.new Thread1();
        Thread t = new Thread(t1);
        t.start();
        System.out.printf("主线程开始休眠......%s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Thread.sleep(1000L);
        t.interrupt();
        System.out.println(t.isInterrupted());

        System.out.printf("主线程结束休眠......%s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        t.join();

        System.out.println("主线程执行完了！");
    }

    public class Thread1 implements Runnable {

        @Override
        public void run() {
            System.out.println("开始执行线程方法！");
            System.out.printf("开始休眠......%s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                System.out.println(Thread.interrupted());
                e.printStackTrace();
            }
            System.out.printf("休眠结束......%s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            for (int i = 10; i > 0; i--) {
                System.out.printf("倒计时:%d\n", i);
            }
        }
    }
}
