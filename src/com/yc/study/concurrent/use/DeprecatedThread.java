package com.yc.study.concurrent.use;

import com.yc.study.concurrent.util.SleepUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ycrag
 */
public class DeprecatedThread {

    public static void main(String[] args) throws Exception {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "Run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }, "PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        printThread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.resume();
        System.out.println("main suspend resume at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.stop();
        System.out.println("main suspend stop at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }
}
