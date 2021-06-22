package com.yc.study.thread.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {

    long time;

    String name;

    public Message(String name, long time, TimeUnit timeUnit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? timeUnit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Message message = (Message) o;
        long diff = time - message.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
