package com.company;
//------------------------------------------------------------------------------------------
//How would you design a stack which, in addition to push and pop, also has a function min which returns the minimum element?
// Push, pop and min should all operate in O(1) time
//------------------------------------------------------------------------------------------

import java.util.Stack;

class MinStack {
    Stack<Integer> fullstack;
    Stack<Integer> minstack;

    public MinStack() {
        this.fullstack = new Stack<Integer>();
        this.minstack = new Stack<Integer>();
    }

    public void push(int val) {
        if(minstack.empty() || minstack.peek() >= val) {
            minstack.push(val);
        }
        fullstack.push(val);
    }

    public int pop() {
        if(fullstack.peek() == minstack.peek()) {
            minstack.pop();
        }
        return fullstack.pop();
    }

    public int min() {
        return minstack.peek();
    }

    void print() {
        System.out.println(fullstack);
    }

}

public class Main {

    public static void main(String[] args) {
	// write your code here
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        System.out.println(minStack.min());
        minStack.print();

        minStack.push(0);
        System.out.println(minStack.min());
        minStack.print();

        minStack.pop();
        System.out.println(minStack.min());
        minStack.print();
    }
}
