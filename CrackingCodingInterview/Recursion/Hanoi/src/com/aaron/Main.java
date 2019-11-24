package com.aaron;

import java.util.Stack;

public class Main {

    //A: from, C: target, B: temp, n: number of disks
    public static void move(Stack<Integer> A, Stack<Integer> C, Stack<Integer> B, int n) {

        if(n == 1) {
            C.push(A.pop());
            return;
        }

        move(A, B, C, n-1);
        int last = A.pop();
        C.push(last);
        move(B, C, A, n-1);
    }

    public static void main(String[] args) {
	// write your code here
        Stack<Integer> A = new Stack<Integer>();
        Stack<Integer> B = new Stack<Integer>();
        Stack<Integer> C = new Stack<Integer>();

        int numberofDisk = 5;
        for(int i = numberofDisk; i >0; i--) {
            A.add(i);
        }

        System.out.println(A);
        System.out.println(B);
        System.out.println(C);

        move(A, C, B, 5);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);


    }
}
