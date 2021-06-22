package com.yc.study.thread.create;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(() -> {
            int count = 0;
            for (int i = 1; i <= 100; i++) {
                count = count + i;
            }
            return count;
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}
