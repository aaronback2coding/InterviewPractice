package com.aaron;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {


    public static ArrayList<int[]> getCombinations(int n) {


        ArrayList<int[]> result = new ArrayList<>();

        int remaining = n;
        for(int i = 0; i < n/25 + 1; i++) {
            int remaining10 = remaining;
            for(int j = 0; j < remaining/10 + 1; j++) {
                int remaining5 = remaining10;
                for(int k = 0; k < remaining10/5 + 1; k++) {
                    int[] arr = new int[4];
                    arr[0] = i;
                    arr[1] = j;
                    arr[2] = k;
                    arr[3] = remaining5;
                    result.add(arr);
                    remaining5 -= 5;
                }
                remaining10 -= 10;
            }
            remaining -= 25;
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<int[]> result = getCombinations(100);
        System.out.println(result.size());
        for(int[] arr:result) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
	// write your code here
    }
}
