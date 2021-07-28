package com.yc.study.test;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

    public String name = "factory thread";

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(name);
    }
}
