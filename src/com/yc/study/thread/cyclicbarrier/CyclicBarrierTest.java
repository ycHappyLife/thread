package com.yc.study.thread.cyclicbarrier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    public static String printDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println(printDate());
        try {
            executorService.execute(new TravelTask(cyclicBarrier, "舵意", 6));
            executorService.execute(new TravelTask(cyclicBarrier, "我", 5));
            executorService.execute(new TravelTask(cyclicBarrier, "xxx", 3));
        } finally {
            executorService.shutdown();
        }

    }
}
