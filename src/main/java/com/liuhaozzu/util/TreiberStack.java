package com.liuhaozzu.util;

import java.util.concurrent.atomic.AtomicReference;

/**
 * an un-blocked stack implemented with CAS
 * @author liuhaozzu
 * @param <E>
 */
public class TreiberStack<E> {
    private AtomicReference<Node<E>> top = new AtomicReference<>();
    private class Node<E>{
        private E item;
        private Node<E> next;

        Node(E item) {
            this.item = item;
        }
    }

    /*Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));*/

    public void push(E item){
        Node<E> newNode = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newNode.next = oldHead;

        } while (!top.compareAndSet(oldHead, newNode));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }
}
