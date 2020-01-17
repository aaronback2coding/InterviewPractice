package com.company;

public class Node<T> {
    T val;
    Node<T> next = null;

    public Node(T val) {
        this.val = val;
    }
}
