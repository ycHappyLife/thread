package com.yc.study.thread.concurrenthashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap源码中相关方法测试
 */
public class HashMapDemo {

    public static void main(String[] args) {
        //当i<cap时，i&cap会==0
        System.out.println(testResize(10, 16));
        //当i>cap时,i&cap不等于0
        System.out.println(testResize(23, 16));
        //通过测试可知,e.hash & oldCap(原HashMap的容量)，可以判断当前节点是比原容量小，还是大
        //小的放到原来索引位置，大的放到旧索引+newCap的新索引位置

        // 测试传递引用，再修改引用上的节点，另外一个节点是否也会发生变化
        Node<Integer> node = new Node<>(5);
        Node<Integer> head = node;
        Node<Integer> tail = node;
        tail.next = new Node<Integer>(10);
        System.out.println(head.next.item);

        StringBuffer aa = new StringBuffer("aa");
        StringBuffer b = aa;
        StringBuffer c = aa;
        c.append("dd");
        System.out.printf("%s", b.toString());

    }

    /**
     * 测试resize方法中 这个判断的含义(e.hash & oldCap) == 0
     * 这个判断用来区别扩容后同一链表节点是存放在原来的索引还是扩容后的索引
     * @return int
     */
    private static int testResize(int i, int cap) {
        return i & cap;
    }

    // 测试节点
    static class Node<E> {
        E item;
        Node next;

        public Node(E item) {
            this.item = item;
            next = null;
        }
    }
}
