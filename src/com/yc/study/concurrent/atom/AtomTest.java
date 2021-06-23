package com.yc.study.concurrent.atom;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * @author ycrag
 */
public class AtomTest {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(1);
        System.out.println(i.getAndAdd(1));
        System.out.println(i.getAndIncrement());
        System.out.println(i.intValue());
        int j = 0;
        do {
            j = 1;
        } while(j == 0);
        System.out.println(j);
        while(j==0) {
            j = 1;
        }
        System.out.println(j);
    }

}
