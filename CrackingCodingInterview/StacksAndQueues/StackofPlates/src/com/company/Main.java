package com.company;
//------------------------------------------------------------------------------------------
//Imagine a (literal) stack of plates
//        If the stack gets too high, it might topple
//        Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold
//        Implement a data structure SetOfStacks that mimics this
//        SetOfStacks should be composed of several stacks, and should create a new stack once the previous one exceeds capacity   S
//        etOfStacks push() and SetOfStacks pop() should behave identically to a single stack (that is,
//        pop() should return the same values as it would if there were just a single stack)
//------------------------------------------------------------------------------------------

import java.util.Stack;

class SetofStacks {
    int stackMaxCapacity = 5;
    Stack<Integer> currentStack;
    Stack<Stack<Integer>> stackofStacks;

    public SetofStacks(int stackMaxCapacity) {
        this.stackMaxCapacity = stackMaxCapacity;
        this.stackofStacks = new Stack<Stack<Integer>>();
        this.currentStack = new Stack<>();
        stackofStacks.push(this.currentStack);
    }

    public void push(int val) {
        if(currentStack.size() == stackMaxCapacity) {
            Stack<Integer> newStack = new Stack<Integer>();
            stackofStacks.push(newStack);
            currentStack = newStack;
        }
        currentStack.push(val);
    }

    public int pop() {
        if(currentStack.empty()) {
            stackofStacks.pop();
            currentStack = stackofStacks.peek();
        }
        return currentStack.pop();
    }

    void print() {
        for(Stack<Integer> stack: stackofStacks) {
            System.out.println(stack);
        }
    }

}

public class Main {

    public static void main(String[] args) {
        // write your code here
        SetofStacks ss = new SetofStacks(5);
        for(int i = 0; i < 20; i++) {
            ss.push(i);
        }
        ss.print();
        for(int i = 0; i < 19; i++) {
            ss.pop();
        }
        ss.print();

    }
}
