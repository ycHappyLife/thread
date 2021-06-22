package com.yc.study.concurrent.use;

/**
 * @author ycrag
 */
public class ThreadDemo {

    public static class ThreadTest extends Thread {
        private String name;

        public ThreadTest(String name) {
            this.name = name;
        }
        @Override
        public void run(){
            System.out.println(name);
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ThreadTest t = new ThreadTest();
//        t.run();
        ThreadTest a = new ThreadTest("a");
        ThreadTest b = new ThreadTest("b");
        a.run();
        b.run();


    }
}
