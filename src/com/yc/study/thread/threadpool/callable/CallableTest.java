package com.yc.study.thread.threadpool.callable;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) {
        ExecutorService executor = null;
        int count = 10;
        try {
            executor = Executors.newCachedThreadPool();
            CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
            for (int i = 0; i < count; i++) {
                FactorialCalculator factorialCalculator = new FactorialCalculator(i);
                completionService.submit(factorialCalculator);
            }

            for (int i = 0; i < count; i++) {
                Future<String> future = completionService.take();
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
}
