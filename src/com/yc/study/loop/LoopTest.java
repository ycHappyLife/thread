package com.yc.study.loop;

public class LoopTest {

    public static void main(String[] args) {
        label:
        for (int i = 1; i < 10; i++) {
            System.out.println("out loop param:" + i);
            for (int j = 11; j < 14; j++) {
                if(i == 3) {
                    break label;
                }
                System.out.println("inner loop param:" + j);
            }
        }

    }

}
