package com.aaron;

//---------------------------------------------------------------------------------------------------------
// Sort Stack
// Learning: your intuition of the logic might be functional but awkward. logic is beautiful, and with the right
//  transformation, you can map the awkward to elegant.
//  in this example, your original logic is:
//  check condition, while, do, and then check,
//  it should be really just while (check condition), do
//  also, the other original setup is while(!A & !B) if A, if B
// a more elegant way is while A, do, while B, do.
//---------------------------------------------------------------------------------------------------------



// Sort array with merge sort

// Sort array with quick sort

// Sort stack with merge sort

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    // Sort stack with insert sort
    public static void Sort(Stack<Integer> stack) {

        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();

        //insert and keep order
        while(!stack.empty()) {
            int val = stack.pop();

            //find the pose and insert
            while ( !right.empty() && val > right.peek() ) {
                left.push(right.pop());
            }

            while ( !left.empty() && val < left.peek() ) {
                right.push(left.pop());
            }
            left.push(val);
        }

        //move to right, and then put back to the original
        while(!left.empty()) {
            right.push(left.pop());
        }
        while(!right.empty()) {
            stack.push(right.pop());
        }
    }

    // Sort stack with insert sort, with recurcion
    public static void Merge(int[] arr, int leftStart, int leftEnd, int rightStart, int rightEnd) {

        if(rightEnd < 0 || rightStart <0)
            return;

        int leftSize = leftEnd - leftStart + 1;
        int rightSize = rightEnd - rightStart + 1;
        int[] buffer = new int[leftSize + rightSize];
        assert(rightStart == leftEnd + 1);

        int i = leftStart;
        int j = rightStart;
        int k = 0;

        while(i <= leftEnd || j <= rightEnd) {
            if (i <= leftEnd && (j > rightEnd || arr[i] <= arr[j]) ) {
                buffer[k] = arr[i];
                i++;
            } else if(j <= rightEnd && (i > leftEnd || arr[i] > arr[j])) {
                buffer[k] = arr[j];
                j++;
            }
            k++;
        }

        System.arraycopy(buffer, 0, arr, leftStart, leftSize + rightSize);
    }


    public static void MergeSort(int[] arr, int start, int end) {

        int length = end - start + 1;
        if (length <= 1) {
            return;
        }

        int leftStart = start;
        int leftEnd = start + length / 2 - 1;

        int rightStart = leftEnd + 1;
        int rightEnd = end;


        MergeSort(arr, leftStart, leftEnd);
        MergeSort(arr, rightStart, rightEnd);

        Merge(arr, leftStart, leftEnd, rightStart, rightEnd);

    }

    private static int powerof2(int i) {
        return (int) Math.pow(2, i);
    }

    // the iteration version
    public static void MergeSort2(int[] arr, int start, int end) {
        int length = end - start + 1;
        int i = 0;
        while (powerof2(i) < length * 2) {
            int offset, leftStart, leftEnd, rightStart, rightEnd;
            int j = 0;
            while (true) {
                offset = powerof2(i+1) * j;
                leftStart = 0 + offset;
                leftEnd = powerof2(i) - 1 + offset;
                rightStart = powerof2(i) + offset;
                rightEnd = powerof2(i+1) - 1 + offset;

                if (end < leftStart) {
                    break;
                } else if (end < leftEnd) {
                    leftEnd = end;
                    rightStart = -1;
                    rightEnd = -1;
                } else if (end < rightStart) {
                    rightStart = -1;
                    rightEnd = -1;
                } else if (end < rightEnd) {
                    rightEnd = end;
                }

                Merge(arr, leftStart, leftEnd, rightStart, rightEnd);
                j++;
            }
            i++;
        }


    }



    public static void main(String[] args) {

//        Stack<Integer> us = new Stack<>();
//        us.push(3);
//        us.push(1);
//        us.push(7);
//        us.push(2);
//        us.push(9);
//        us.push(6);
//        System.out.println(us);
//        Sort(us);
//        System.out.println(us);

//        int[] a = {1, 3, 7, 2, 4, 9};
//        Merge(a, 0, 2, 3, 5);
//        System.out.println(Arrays.toString(a));

        int[] arr = {3, 1, 7, 2, 9, 6, 8, 9, 100, 2};
        MergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int[] arr3 = {0, 1, 2, 3, 4};
        int[] arr2 = {3, 1, 7, 2, 9, 6, 8, 9, 100, 2};

        MergeSort2(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));



    }
}
