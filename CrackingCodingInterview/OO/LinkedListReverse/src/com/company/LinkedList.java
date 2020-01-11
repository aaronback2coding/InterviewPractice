package com.company;

public class LinkedList<T> {
    Node<T> head = null;

    Node<T> add(T val) {
        Node<T> node = new Node<>(val);
        if(head == null) {
            head = node;
        } else {
            Node<T> cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        return node;
    }

    void print() {
        Node<T> cur = head;
        while(cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    void reverse() {
        Node<T> cur = head;
        Node<T> prev = null;
        while(cur != null) {
            Node<T> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }

}
