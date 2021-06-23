package com.yc.study.thread.forkjoin.recursivetask;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

    private final static int THRESHOLD = 20;

    private int nums[];

    private int start;

    private int end;

    public SumTask(int[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += nums[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(nums, start, mid);
            SumTask rightTask = new SumTask(nums, mid, end);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();
        }

    }
}
