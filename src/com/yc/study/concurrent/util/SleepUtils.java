package com.yc.study.concurrent.util;

import java.util.concurrent.TimeUnit;

/**
 * the thread sleep method util
 * @author ycrag
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
