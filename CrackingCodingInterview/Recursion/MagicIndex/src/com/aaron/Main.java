package com.aaron;

// learning: partition the problem into smaller ones, to pursue log(n) performance.

import java.util.ArrayList;

public class Main {
    //when elements can duplicate (in this case the basic assumption of the magic index is unique, and either on left or
    //right side is no longer valid
    private static ArrayList<Integer> getMagicIndex2(int arr[], int start, int end) {

        if(start > end)
            return null;
        int mid = (start + end) / 2;
        ArrayList<Integer> result = new ArrayList<>();

        if(mid == arr[mid]) {
            result.add(mid);
        }

        ArrayList<Integer> left = null;
        ArrayList<Integer> right = null;

        int leftend = Math.min(mid - 1, arr[mid]);
        int rightstart = Math.max(mid+1, arr[mid]);
        left = getMagicIndex2(arr, start, leftend);
        right = getMagicIndex2(arr, rightstart, end);

//        if(mid < arr[mid]) {
//            left = getMagicIndex2(arr, start, mid - 1);
//            right = getMagicIndex2(arr, arr[mid], end);
//        }
//
//        if(mid > arr[mid]) {
//            left = getMagicIndex2(arr, start, arr[mid]);
//            right = getMagicIndex2(arr, mid + 1, end);
//
//        }
        if(left != null)
            result.addAll(left);
        if(right != null)
            result.addAll(right);
        return result;
    }

    public static ArrayList<Integer> getMagicIndex2(int arr[]) {
        return getMagicIndex2(arr, 0, arr.length - 1);
    }


    //when elements are distinct
    private static int getMagicIndex(int arr[], int start, int end) {
        if(start > end)
            return -1;
        int mid = (start + end) / 2;
        if(mid == arr[mid])
            return mid;
        if(mid < arr[mid])
            return getMagicIndex(arr, start, mid - 1);
        else
            return getMagicIndex(arr, mid + 1, end);

    }

    public static int getMagicIndex(int arr[]) {
        return getMagicIndex(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
	// write your code here
        int arr[] = {-3, -1, 2, 5, 7, 20, 90, 1000};
        int result = getMagicIndex(arr);
        if(result >=0 )
            System.out.println(result);


        int arr2[] = {-3, -1, 2, 5, 7, 7, 7, 7, 10000, 1000002};
        ArrayList<Integer> result2 = getMagicIndex2(arr2);
        if(result2 != null) {
            System.out.println(result2);
        }
    }
}
