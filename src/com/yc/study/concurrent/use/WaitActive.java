package com.yc.study.concurrent.use;

import com.yc.study.concurrent.util.SleepUtils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

/**
 * @author ycrag
 */
public class WaitActive {

    private volatile int result = 0;

    /*private People people;*/

    public static void main(String[] args) {
        WaitActive waitActive = new WaitActive();
        waitActive.getResult(2000);

    }

    public synchronized int getResult(int millis) {
        while (millis > 0) {
            try {
                System.out.println("执行wait方法前时间：" + LocalTime.now());
                wait(millis);
                System.out.println("执行wait方法后时间：" + LocalTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    public synchronized void batchInsert() {
        SleepUtils.second(2);
        /*People people = new People("张三", "男");*/
    }

    /*class People {

        private String name;
        private String sex;

        public People(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }
    }*/
}
