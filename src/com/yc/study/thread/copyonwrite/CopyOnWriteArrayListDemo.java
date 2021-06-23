package com.yc.study.thread.copyonwrite;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            copyOnWriteArrayList.add(i);
        }
        new Thread(() -> {
            for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s输出元素%s%n", Thread.currentThread().getName(), copyOnWriteArrayList.get(i));
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (copyOnWriteArrayList.get(i) % 2 == 0) {
                    System.out.printf("%s删除元素%s%n", Thread.currentThread().getName(), copyOnWriteArrayList.remove(i));
                }
            }
        }).start();
        Thread.sleep(10000);
        for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
            System.out.printf("剩余元素:%s%n",copyOnWriteArrayList.get(i));
        }
    }
}
