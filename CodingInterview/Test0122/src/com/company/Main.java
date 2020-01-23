package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Main {

    public static void printMaxSequence(int[] a) {
        int[] max = new int[a.length];
        int[] history = new int[a.length];
//        ArrayList<Integer>[] history = new ArrayList[a.length];
        int max_max = 0;
        int max_i = 0;

        for(int i = 0; i < a.length; i++) {
            max[i] = 0;
            boolean maxFound = false;
            for (int j = 0; j < i; j++) {
                if (a[i] >= a[j]) {
                    if(max[j] >= max[i]) {
                        max[i] = max[j];
                        history[i] = j;
                        maxFound = true;
                    }
                }
            }
            if(maxFound)
                max[i] += 1;
            if(max[i] >= max_max) {
                max_max = max[i];
                max_i = i;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        int i = max_i;
        while(max[i] != 0) {
            result.add(a[i]);
            i = history[i];
        }
        result.add(a[i]);

        for(int j = 0; j < result.size(); j++) {
            System.out.println(result.get(result.size() - j - 1));
        }

    }

    // test cases - null, 1 item, 2 item, curve variation - all down, all up, up down, down up.

    public static void main(String[] args) {
        int[] a= {1, 3, 2, 4, 5, 1};
        printMaxSequence(a);

    }
}
