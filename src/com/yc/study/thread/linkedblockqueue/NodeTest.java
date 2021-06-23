package com.yc.study.thread.linkedblockqueue;

public class NodeTest {

    transient Node first;

    transient Node last;

    public NodeTest() {
        first = last = new Node(null);
    }

    public class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
            this.next = null;
        }

    }

    public static void main(String[] args) {
        Node item = new NodeTest().new Node(new Integer(1));
        NodeTest nodeTest = new NodeTest();
        nodeTest.last.next = item;
        nodeTest.last = nodeTest.last.next;
        System.out.println("come in");
    }
}
