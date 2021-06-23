package com.yc.study.thread.copyonwrite;

public class ArrayTest {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] na = new int[10];
        System.arraycopy(a, 0, na, 0, 2);
        System.arraycopy(a, 2, na, 3, 3);
        for (int i = 0; i < na.length; i++) {
            System.out.println(na[i]);
        }
        System.out.println("---------------");
        System.out.println(20 & 3);
    }
}
