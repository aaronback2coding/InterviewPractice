package com.aaron;


//-----------------------------------------------------------------------------------------------------------
// Write code to remove duplicates from an unsorted linked list FOLLOW UP
// How would you solve this problem if a temporary buffer is not allowed?
//-----------------------------------------------------------------------------------------------------------

import java.util.*;

class LinkList<T> {
    Node head = null;
    public Node lastNode() {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            pre = cur;
            cur = cur.next;
        }
        return pre;
    }
    public void append(T data) {
        Node node = new Node(data);
        if(head == null) {
            head = node;
            return;
        }
        lastNode().next = node;
    }
    public void print() {
        System.out.println("..........................................");
        Node<T> cur = head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
    public void removeDuplicates() {
        HashSet<T> hashset = new HashSet<T>();
        Node<T> cur = head;
        Node<T> prev = null;
        while (cur != null) {
            if(!hashset.add(cur.data)) {
                //if duplicates, remove!
                prev.next = cur.next;
                cur.next = null;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
    }

    public void removeDuplicates2() {
        Node<T> cur = head;
        Node<T> prev = null;
        while (cur != null) {
            Node<T> curRunner = cur.next;
            Node<T> prevRunner = cur;
            while(curRunner != null) {
                if(cur.data.equals(curRunner.data)) {
                    //if duplicates, remove!
                    prevRunner.next = curRunner.next;
                    curRunner.next = null;
                    curRunner = prevRunner.next;
                } else {
                    prevRunner = curRunner;
                    curRunner = curRunner.next;
                }
            }
            prev = cur;
            cur = cur.next;
        }
    }

}


class Node<T> {
    T data;
    Node next = null;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }



}

public class Main {

    public static void main(String[] args) {
        LinkList<String> ll = new LinkList<String> ();
        ll.append("abc");
        ll.append("b");
        ll.append("e");
        ll.append("a");
        ll.append("d");
        ll.append("e");
        ll.append("abc");

        ll.print();
        ll.removeDuplicates2();
        ll.print();


    }
}

//        // Creating object of class linked list
//        LinkedList<String> object = new LinkedList<String>();
//
//        // Adding elements to the linked list
//        object.add("A");
//        object.add("A");
//        object.addLast("C");
//        object.addFirst("D");
//        object.add(2, "E");
//        object.add("F");
//        object.add("G");
//        System.out.println("Linked list : " + object);
//
//        // Removing elements from the linked list
//        object.remove("C");
//        object.remove(3);
//        object.removeFirst();
//        object.removeLast();
//        System.out.println("Linked list after deletion: " + object);
//
//        // Finding elements in the linked list
//        boolean status = object.contains("E");
//
//        if(status)
//            System.out.println("List contains the element 'E' ");
//        else
//            System.out.println("List doesn't contain the element 'E'");
//
//        // Number of elements in the linked list
//        int size = object.size();
//        System.out.println("Size of linked list = " + size);
//
//        // Get and set elements from linked list
//        Object element = object.get(2);
//        System.out.println("Element returned by get() : " + element);
//        object.set(2, "Y");
//        System.out.println("Linked list after change : " + object);
// write your code here


//-----------------------------------------------------------------------------------------------------------
// template
//-----------------------------------------------------------------------------------------------------------

//public class Main {
//
//    public static void main(String[] args) {
//	// write your code here
//    }
//}
