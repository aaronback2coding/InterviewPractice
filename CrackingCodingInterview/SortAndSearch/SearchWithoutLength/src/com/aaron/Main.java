package com.aaron;

// Leanring - do not go down to one thought process and try to solve a narrow and specific problem with brute force
//  while it might work, there might be better solution available if you step back a little bit and relax your thinking
// in this example, you can solve the problem about where the end is in the findRange funciton, and make it straightforward
// to do binary search, but that's not necessary at all.
// instead, you can leave the ambiguity into binary search, and keep the find Range very simple. As it turns out, binarySearch
// can actually handle the case where we do not know where the end is very naturally.

public class Main {

    private static int get(int[] arr, int index) {
        if(index < arr.length)
            return arr[index];
        else
            return -1;
    }


    public static int findRange(int[] arr, int val){
        if(val < 0)
            return -1;
        if(get(arr, 0) == val)
            return 0;
        int i = 1;
        int curVal = get(arr, i);
        if(val <= curVal)
            return i;
        while(val > curVal && curVal >= 0) {
            i *= 2;
            curVal = get(arr, i);
        }
        return i / 2;
    }


    public static int binarySearch(int[]arr, int val, int start, int end) {

        if(end - start == 1) {
            if(get(arr, start) == val)
                return start;
            if(get(arr, end) ==  val)
                return end;
            return -1;
        }

        if(end - start == 0) {
            if(get(arr, start) == val)
                return start;
            return -1;
        }

        int mid = (start + end) / 2;
        int midVal = get(arr, mid);
        if (midVal >= 0 && val > midVal) {
            return binarySearch(arr, val, mid + 1, end);
        }
        if (midVal >= 0 && val <= midVal) {
            return binarySearch(arr, val, start, mid);
        }
        if (midVal < 0) {
            return binarySearch(arr, val, start, mid);
        }
        return -1;
    }

    public static int search(int[]arr, int val) {
        int start = findRange(arr, val);
        int end = start * 2;
        return binarySearch(arr, val, start, end);
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 4, 5, 6, 8, 9, 10};
        System.out.println(search(arr, 11));

    }
}
