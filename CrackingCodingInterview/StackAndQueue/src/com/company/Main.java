package com.company;
//-------------------------------------------------------------------------------------------------------------------
//Describe how you could use a single array to implement three stacks
// shift next, shift circular
// learning: trade off is everywhere.
// learning: be paitient. break down the problem into small manage pieces. add one funciton after the other. build the
// full feature set gradually.
// in this example, i did: basic insert, shift with 1 layer, shift with 2 layer, shift with circular buffer. it tooks 4
// iterations, but that's how i can manage the complexity!
//-------------------------------------------------------------------------------------------------------------------

class StackFullException extends Exception
{
    public StackFullException(String message)
    {
        super(message);
    }
}
class StackEmptyException extends Exception
{
    public StackEmptyException(String message)
    {
        super(message);
    }
}

class MultiStack {

    class Stack {
        int start; //include buffer [start], except for end == start;
        int end; //not include buffer[end]

        public Stack(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private int stackCount = 3;
    private int stackDefaultCapacity = 10;
    private int[] buffer;
    private int bufferSize;
    private Stack[] stacks;

    public MultiStack(int stackCount, int stackDefaultCapacity) {
        this.stackCount = stackCount;
        this.stackDefaultCapacity = stackDefaultCapacity;
        this.bufferSize = stackCount * stackDefaultCapacity;
        this.buffer = new int[bufferSize];
        this.stacks = new Stack[stackCount];
        for(int i = 0; i<stacks.length; i++) {
            stacks[i] = new Stack(i * stackDefaultCapacity, i * stackDefaultCapacity);
        }
    }

    boolean empty(int stackIndex) {
        return false;
    }

    private boolean shift(int stackIndex, int shifttimes) {
        if(shifttimes > 1)
            return false;

        Stack stack = stacks[stackIndex];
        Stack stackNext = stacks[(stackIndex + 1) % stackCount];

        if(stack.end == bufferSize) {
            return false;
        }

        if(stack.end == stackNext.start) {
            if(!shift(stackIndex +1, shifttimes + 1)) {
                return false;
            }
        }

        //move content
        System.arraycopy(buffer, stack.start, buffer, stack.start + 1, stack.end - stack.start);
        stack.start++;
        stack.end++;
        return true;
    }

    void push(int stackIndex, int val) throws StackFullException{
        Stack stack = stacks[stackIndex];
        Stack stackNext = stacks[(stackIndex+1) % stackCount];

        //check availability
        if(stack.end == stackNext.start) {
            if(!shift((stackIndex + 1)%stackCount, 0)) {
                throw new StackFullException("");
            }
        }
        //insert
        buffer[stack.end] = val;
        stack.end = (stack.end + 1) % bufferSize;
    }

    int pop(int stackIndex)  throws StackEmptyException{
        Stack stack = stacks[stackIndex];
        //check empty
        if(stack.end == stack.start)
            throw new StackEmptyException("");
        //remove and return
        int result = buffer[stack.end - 1];
        buffer[stack.end - 1] = 0;
        stack.end --;
        return result;
    }

    int peek(int stackIndex) throws StackEmptyException{
        Stack stack = stacks[stackIndex];
        //check empty
        if(stack.end == stack.start)
            throw new StackEmptyException("");
        //remove and return
        return buffer[stack.end - 1];
    }

    void print() {
        StringBuilder sb = new StringBuilder();
        for(int i: buffer) {
            sb.append(i);
            sb.append(", ");
        }
        System.out.println(sb);
    }
}



public class Main {

    public static void main(String[] args) throws StackFullException, StackEmptyException {

        MultiStack ms = new MultiStack(3, 10);
        int i;
        for (i = 0; i < 10; i++) {
            ms.push(0, i);
        }
        for (int j = 0; j < 2; j++) {
            ms.push(1, 2);
        }
        for (int j = 0; j < 19; j++) {
            ms.push(2, 3);
        }
//        for (; i < 25; i++) {
//            ms.push(0, i);
//        }



        ms.print();

    }
}
