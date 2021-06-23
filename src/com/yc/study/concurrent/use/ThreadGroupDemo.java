package com.yc.study.concurrent.use;

/**
 * @author ycrag
 */
public class ThreadGroupDemo {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("当前线程组名称：" + Thread.currentThread().getThreadGroup().getName());
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
        });
        t.start();
        System.out.println("main线程组名称：" + Thread.currentThread().getThreadGroup().getName());
        System.out.println("main线程名：" + Thread.currentThread().getName());
    }
}
