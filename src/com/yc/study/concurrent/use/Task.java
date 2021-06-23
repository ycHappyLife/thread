package com.yc.study.concurrent.use;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ycrag
 */
public class Task implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws Exception{
        /*ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        System.out.println(result.get());*/
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> result = new FutureTask<>(task);
        executor.submit(result);
        System.out.println(result.get());
    }
}
