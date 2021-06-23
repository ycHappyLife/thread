package com.yc.study.thread.sychronizeduse;

public class SynchronizeDemo {

    public void method() {
        synchronized (this) {
            System.out.println("method1 start");
        }
    }
}
