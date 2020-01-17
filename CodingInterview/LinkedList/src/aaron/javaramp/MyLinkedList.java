package aaron.javaramp;

import java.util.Stack;

public class MyLinkedList<T> {
    Node<T> head = null;
    Node<T> tail = null;

    Node<T> add(T val) {
        Node<T> node = new Node<>(val);
        if(tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
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

    void _printBackward(Node<T> start) {
        if(start == null)
            return;
        _printBackward(start.next);
        System.out.println(start.val);

    }

    // option 1 - use prev + tail. this is super easy
    // option 2 - if we cannot use prev + tail, then find last, change link along the way, and iterate back. can use recursion
    // option 3 - use some extra data structure - e.g. a stack - no recursion needed.
    // option 4 - basic recursion - n -> n-1 + 1 vs. 1 + n-1
    void printBackward() {
        _printBackward(head);
    }

    void printBackward2() {
        Node<T> cur = head;
        Stack<T> stack = new Stack<>();

        while(cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        while(!stack.empty()) {
            System.out.println(stack.pop());
        }

    }

    void printBackward3() {
        Node<T> cur = head;
        Node<T> last = null;

        while(cur != null) {
            last = cur;
            cur = cur.next;
        }

        while(last != head) {
            System.out.println(last.val);
            cur = head;
            Node<T> prev = null;
            while(cur != last) {
                prev = cur;
                cur = cur.next;
            }
            last = prev;
        }

        System.out.println(head.val);
    }

    void printBackward4() {
        Node<T> cur = head;
        Node<T> last = null;
        while(last !=  head) {
            cur = head;
            while(cur.next != last) {
                cur = cur.next;
            }
            System.out.println(cur.val);
            last = cur;
        }
    }


}
