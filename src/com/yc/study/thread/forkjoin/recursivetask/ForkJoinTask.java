package com.yc.study.thread.forkjoin.recursivetask;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinTask {
    public static void main(String[] args) throws Exception{
        int[] nums = new int[10000];
        Random random = new Random();
        int total = 0;
        long start = System.nanoTime();
        for (int i = 0; i < nums.length; i++) {
            int temp = random.nextInt(100);
            nums[i] = temp;
            total += nums[i];
        }
        long end = System.nanoTime();
        System.out.println("初始化数组用时1：" + (end - start) + "纳秒，初始化数组总和:" + total);

        long startTask = System.nanoTime();
        SumTask sumTask = new SumTask(nums, 0, nums.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        java.util.concurrent.ForkJoinTask<Integer> future = forkJoinPool.submit(sumTask);
        long endTask = System.nanoTime();
        System.out.println("初始化数组用时2：" + (endTask - startTask) + "纳秒，初始化数组总和:" + future.get());
        forkJoinPool.shutdown();
    }
}
