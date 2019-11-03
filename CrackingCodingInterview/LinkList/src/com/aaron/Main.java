package com.aaron;
//-----------------------------------------------------------------------------------------------------------
//You have two numbers represented by a linked list, where each node contains a single digit
// The digits are stored in reverse order, such that the 1’s digit is at the head of the list
// Write a function that adds the two numbers and returns the sum as a linked list
//EXAMPLE
//      Input: (3 -> 1 -> 5), (5 -> 9 -> 2)
//      Output: 8 -> 0 -> 8
//-----------------------------------------------------------------------------------------------------------

import java.util.*;

class Number {
    LinkList<Integer> list = null;
    public Number(int[] arr) {
        this.list = new LinkList<>();
        for(int i: arr) {
            this.list.append(i);
        }
    }
    public Number() {
        this.list = new LinkList<>();
    }

    public static Number add(Number left, Number right) {
        if (left == null)
            return null;
        if (right == null)
            return null;
        Number result = new Number();
        Node<Integer> leftCur = left.list.getHead();
        Node<Integer> rightCur = right.list.getHead();
        int carryDigit = 0;
        while (leftCur!= null || rightCur != null || carryDigit != 0) {
            int leftDigit = (leftCur == null) ? 0 : leftCur.data;
            int rightDigit = (rightCur == null) ? 0 : rightCur.data;
            int sum = leftDigit + rightDigit + carryDigit;
            carryDigit = 0;
            int resultDigit = 0;
            if (sum >= 10) {
                resultDigit = sum % 10;
                carryDigit = (sum - resultDigit) / 10;
            } else {
                resultDigit = sum;
            }
            result.list.append(resultDigit);
            if (leftCur != null)
                leftCur = leftCur.next;
            if (rightCur != null)
                rightCur = rightCur.next;
        }
        return result;
    }

    public void print() {
        if(list == null)
            return;
        list.print(true);
    }

}

class LinkList<T> {
    private Node head = null;

    public Node getHead() {
        return head;
    }

    public Node lastNode() {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            pre = cur;
            cur = cur.next;
        }
        return pre;
    }

    public Node<T> append(T data) {
        Node node = new Node(data);
        if(head == null) {
            head = node;
        } else {
            lastNode().next = node;
        }
        return node;
    }

    public void print(boolean inline) {
        Node<T> cur = head;
        if(!inline) {
            System.out.println("..........................................");
            while (cur != null) {
                System.out.println(cur.data);
                cur = cur.next;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            while (cur != null) {
                sb.append(cur.data);
                sb.append("->");

                cur = cur.next;
            }
            System.out.println(sb);
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

    public T get(int n, boolean fromFront) {
        if(fromFront) {
            Node<T> cur = head;
            Node<T> prev = null;
            for (int i=0; i<n; i++) {
                if(cur == null) {
                    return null;
                }
                prev = cur;
                cur = cur.next;
            }
            if(prev == null) {
                return null;
            } else {
                return prev.data;
            }
        } else {
            Node<T> frontCur = head;
            Node<T> backCur = head;

            //create distance
            for(int i=0; i<n; i++) {
                if(frontCur == null) {
                    return null;
                }
                frontCur = frontCur.next;
            }
            //move together, when front reach end, back is the last n
            while(frontCur != null) {
                frontCur = frontCur.next;
                backCur = backCur.next;
            }
            return backCur.data;
        }
    }

    public boolean deleteNode(Node<T> node) {
        if(node == null)
            return false;
        if(node.next ==null)
            return false;
        Node<T> next = node.next;
        node.data = next.data;
        node.next = next.next;
        next.next = null;
        return true;
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
        Number left = new Number(new int[] {1, 2, 3});
        left.print();
        Number right = new Number(new int[] {9,9, 7});
        right.print();
        Number result = Number.add(left, right);
        if (result == null)
            return;
        result.print();

        System.out.println(321 + 799);


    }
}

//-----------------------------------------------------------------------------------------------------------
// Exercise 1: find the nth ato last element of a singly linked list
// Exercise 2: Implement an algorithm to delete a node in the middle of a single linked list, given only access to that node
//   EXAMPLE  Input: the node ‘c’ from the linked list a->b->c->d->e
//            Result: nothing is returned, but the new linked list looks like a->b->d->e
//-----------------------------------------------------------------------------------------------------------
//
//import java.util.*;
//
//class LinkList<T> {
//    Node head = null;
//    public Node lastNode() {
//        Node cur = head;
//        Node pre = null;
//        while (cur != null) {
//            pre = cur;
//            cur = cur.next;
//        }
//        return pre;
//    }
//    public Node<T> append(T data) {
//        Node node = new Node(data);
//        if(head == null) {
//            head = node;
//        } else {
//            lastNode().next = node;
//        }
//        return node;
//    }
//    public void print() {
//        System.out.println("..........................................");
//        Node<T> cur = head;
//        while (cur != null) {
//            System.out.println(cur.data);
//            cur = cur.next;
//        }
//    }
//    public void removeDuplicates() {
//        HashSet<T> hashset = new HashSet<T>();
//        Node<T> cur = head;
//        Node<T> prev = null;
//        while (cur != null) {
//            if(!hashset.add(cur.data)) {
//                //if duplicates, remove!
//                prev.next = cur.next;
//                cur.next = null;
//                cur = prev.next;
//            } else {
//                prev = cur;
//                cur = cur.next;
//            }
//        }
//    }
//    public T get(int n, boolean fromFront) {
//        if(fromFront) {
//            Node<T> cur = head;
//            Node<T> prev = null;
//            for (int i=0; i<n; i++) {
//                if(cur == null) {
//                    return null;
//                }
//                prev = cur;
//                cur = cur.next;
//            }
//            if(prev == null) {
//                return null;
//            } else {
//                return prev.data;
//            }
//        } else {
//            Node<T> frontCur = head;
//            Node<T> backCur = head;
//
//            //create distance
//            for(int i=0; i<n; i++) {
//                if(frontCur == null) {
//                    return null;
//                }
//                frontCur = frontCur.next;
//            }
//            //move together, when front reach end, back is the last n
//            while(frontCur != null) {
//                frontCur = frontCur.next;
//                backCur = backCur.next;
//            }
//            return backCur.data;
//        }
//    }
//    public boolean deleteNode(Node<T> node) {
//        if(node == null)
//            return false;
//        if(node.next ==null)
//            return false;
//        Node<T> next = node.next;
//        node.data = next.data;
//        node.next = next.next;
//        next.next = null;
//        return true;
//    }
//}
//
//
//class Node<T> {
//    T data;
//    Node next = null;
//
//    public Node(T data) {
//        this.data = data;
//        this.next = null;
//    }
//}
//
//public class Main {
//
//    public static void main(String[] args) {
//        LinkList<String> ll = new LinkList<String> ();
//
//        ll.append("1");
//        ll.append("2");
//        ll.append("3");
//        Node<String> node4 = ll.append("4");
//        ll.append("5");
//        ll.append("6");
//        ll.append("7");
//
//        ll.print();
//        ll.deleteNode(node4);
//        ll.print();
//
//    }
//}



//-----------------------------------------------------------------------------------------------------------
// Write code to remove duplicates from an unsorted linked list FOLLOW UP
// How would you solve this problem if a temporary buffer is not allowed?
//-----------------------------------------------------------------------------------------------------------
//
//import java.util.*;
//
//class LinkList<T> {
//    Node head = null;
//    public Node lastNode() {
//        Node cur = head;
//        Node pre = null;
//        while (cur != null) {
//            pre = cur;
//            cur = cur.next;
//        }
//        return pre;
//    }
//    public void append(T data) {
//        Node node = new Node(data);
//        if(head == null) {
//            head = node;
//            return;
//        }
//        lastNode().next = node;
//    }
//    public void print() {
//        System.out.println("..........................................");
//        Node<T> cur = head;
//        while (cur != null) {
//            System.out.println(cur.data);
//            cur = cur.next;
//        }
//    }
//    public void removeDuplicates() {
//        HashSet<T> hashset = new HashSet<T>();
//        Node<T> cur = head;
//        Node<T> prev = null;
//        while (cur != null) {
//            if(!hashset.add(cur.data)) {
//                //if duplicates, remove!
//                prev.next = cur.next;
//                cur.next = null;
//                cur = prev.next;
//            } else {
//                prev = cur;
//                cur = cur.next;
//            }
//        }
//    }
//
//    public void removeDuplicates2() {
//        Node<T> cur = head;
//        Node<T> prev = null;
//        while (cur != null) {
//            Node<T> curRunner = cur.next;
//            Node<T> prevRunner = cur;
//            while(curRunner != null) {
//                if(cur.data.equals(curRunner.data)) {
//                    //if duplicates, remove!
//                    prevRunner.next = curRunner.next;
//                    curRunner.next = null;
//                    curRunner = prevRunner.next;
//                } else {
//                    prevRunner = curRunner;
//                    curRunner = curRunner.next;
//                }
//            }
//            prev = cur;
//            cur = cur.next;
//        }
//    }
//
//}
//
//
//class Node<T> {
//    T data;
//    Node next = null;
//
//    public Node(T data) {
//        this.data = data;
//        this.next = null;
//    }
//
//
//
//}
//
//public class Main {
//
//    public static void main(String[] args) {
//        LinkList<String> ll = new LinkList<String> ();
//        ll.append("abc");
//        ll.append("b");
//        ll.append("e");
//        ll.append("a");
//        ll.append("d");
//        ll.append("e");
//        ll.append("abc");
//
//        ll.print();
//        ll.removeDuplicates2();
//        ll.print();
//
//
//    }
//}

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
