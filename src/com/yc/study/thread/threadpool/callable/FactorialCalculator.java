package com.yc.study.thread.threadpool.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalculator implements Callable<String> {

    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public String call() throws Exception {
        int result = 1;

        if (number == 0 || number == 1) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *=i;
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }
        return String.format("%s输出%d的阶乘为：%d\n", Thread.currentThread().getName(), number, result);
    }


}
