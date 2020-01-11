package com.aaron;

// learning - visualize your algoirthm design to help you spot any inefficiency.
//  in this example, the shift is not necessary, if you start from the end.

public class Main {
    public static void rightShift(int[] arr, int index) {
        for(int i = arr.length - 2; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
    }

    public static boolean sortedMerge(int[]target, int targetFreeSpace, int[] source) {

        if(targetFreeSpace < source.length)
            return false;

        int i = 0;
        int j = 0;
        while (j < source.length ) {
            if(target[i] <= source[j]) {
                i++;
            } else {
                rightShift(target, i);
                target[i] = source[j];
                j++;
            }
        }

        return true;


    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9, 0, 0, 0};
        int[] b = {2, 4, 6};
        sortedMerge(a, 3, b);
        System.out.println("   ");
    }
}
