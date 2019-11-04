package com.company;

//--------------------------------------------------------------------------------------------------------------------
// implement a myqueue: a queue using two stacks
//--------------------------------------------------------------------------------------------------------------------


import java.util.Stack;

class Queuewith2Stacks<T> {
    private Stack<T> left;
    private Stack<T> right;
    private boolean leftHeavy;

    public Queuewith2Stacks() {
        this.left = new Stack<T>();
        this.right = new Stack<T>();
        this.leftHeavy = true;
    }

    private void changeMode() {
        Stack<T> stackfrom = leftHeavy ? left: right;
        Stack<T> stackto = leftHeavy ? right: left;
        while(!stackfrom.empty()) {
            stackto.push(stackfrom.pop());
        }
        leftHeavy = !leftHeavy;
    }

    public void add(T val) {
        if(!leftHeavy) {
            changeMode();
        }
        left.push(val);
    }

    public T remove() {
        if(leftHeavy) {
            changeMode();
        }
        return right.pop();
    }

    public T peek() {
        return null;
    }

    public void print() {
        System.out.println(left);
        System.out.println(right);
    }

}

public class Main {

    public static void main(String[] args) {
        Queuewith2Stacks<Integer> myQ = new Queuewith2Stacks<>();
        for(int i = 0; i < 10; i++) {
            myQ.add(i);
        }
        myQ.print();

        myQ.remove();
        myQ.remove();
        myQ.print();

        myQ.add(100);
        myQ.print();

        myQ.remove();
        myQ.print();

        myQ.add(101);
        myQ.print();

    }
}
