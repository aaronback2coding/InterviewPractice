package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Main {

    public static void printMaxSequence(int[] a) {
        int[] max = new int[a.length];
        int[] history = new int[a.length];

        for(int i = 0; i < a.length; i++) {
            max[i] = 0;
            for(int j = 0; j < i; j++) {
                if(a[i] >= a[j]) {
                    max[i] = max[j];
                    history[i] = j;
                }
            }
            max[i] += 1;
        }




    }

    // test cases - null, 1 item, 2 item, curve variation - all down, all up, up down, down up.

    public static void main(String[] args) {
        int[] a= {1, 3, 2, 4, 5, 1};
        printMaxSequence(a);

    }
}
