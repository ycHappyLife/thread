package com.yc.study.thread.forkjoin.recursiveaction;

import java.util.concurrent.RecursiveAction;

public class PrintTask extends RecursiveAction {

    private final static int THRESHOLD = 50;

    private int start;

    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                ForkJoinAction.RESULT_SET.add(i);
                System.out.println(Thread.currentThread().getName() + "i的值\t" + i);
            }
        } else {
            int mid = (start + end) / 2;
            PrintTask leftTask = new PrintTask(start, mid);
            PrintTask rightTask = new PrintTask(mid, end);
            leftTask.fork();
            rightTask.fork();
        }
    }
}
