package com.yc.study.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * completableFuture简单使用demo
 */
public class CompletableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> 1).thenApplyAsync(s -> s + 1);
//        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "hello world");
        System.out.println(task1.get());


    }
}
