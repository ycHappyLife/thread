package com.yc.study.thread.forkjoin.recursiveaction;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkJoinAction {

    public static final Set<Integer> RESULT_SET = new CopyOnWriteArraySet<>();

    public static void main(String[] args) throws Exception {
        validateForkJoin();
    }

    private static void validateForkJoin() throws InterruptedException {
        PrintTask printTask = new PrintTask(0, 3000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(printTask);
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.printf("RESULT_SET的大小是=%s", RESULT_SET.size());
        forkJoinPool.shutdown();
    }
}
