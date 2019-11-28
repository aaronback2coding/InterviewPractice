package com.aaron;

public class Main {

    private static int search(int[]arr, int val, int start, int end) {

        if(end - start == 1) {
            if(arr[end] == val)
                return end;
            if(arr[start] == val)
                return start;
            return -1;
        }
        if(end - start == 0) {
            if(arr[end] == val)
                return end;
            return -1;
        }

        int mid = (start + end) / 2;
        if(arr[mid] >= arr[start] && arr[mid] >= arr[end]) {
            if(val <= arr[mid] && val >= arr[start])
                return search(arr, val, start, mid);
            if(val > arr[mid] || val < arr[start])
                return search(arr, val, mid + 1, end);
        }
        else if(arr[mid] <= arr[start] && arr[mid] <= arr[end]) {
            if(val >= arr[mid] && val <= arr[end])
                return search(arr, val, mid + 1, end);
            if(val > arr[end] || val < arr[mid])
                return search(arr, val, start, mid);
        } else if(arr[mid] > arr[start] && arr[mid] < arr[end]) {
            if(val >= arr[mid])
                return search(arr, val, mid + 1, end);
            else
                return search(arr, val, start, mid);
        }

        return -1;

    }

    public static int search(int[]arr, int val) {
        return search(arr, val, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr2 = {2, 2, 2, 3, 4, 2};
        System.out.println(search(arr2, 4));

        // write your code here
    }
}
