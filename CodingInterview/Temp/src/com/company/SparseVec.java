package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public  class SparseVec {
    class Pair {
        int index;
        int val;
    }

    int length = 0;
    ArrayList<Pair> data = null;

    public SparseVec(int[] array) {
        data = new ArrayList<>();
        length = array.length;
        for(int i = 0; i < array.length; i++) {
            if(array[i] != 0) {
                Pair p = new Pair();
                p.index = i;
                p.val = array[i];
                data.add(p);
            }
        }

    }

    public static int dotProduct(SparseVec v1, SparseVec v2) {
        int result = 0;

        if(v1.data == null || v2.data == null || v1.length != v2.length)
            return result;

        int i = 0;
        int j = 0;
        int size1 = v1.data.size();
        int size2 = v2.data.size();

        while (i < size1 && j < size2){
            Pair p1 = v1.data.get(i);
            Pair p2 = v2.data.get(j);
            if(p1.index > p2.index)
                j++;
            else if(p1.index < p2.index)
                i++;
            else if(p1.index == p2.index) {
                result += p1.val * p2.val;
                i++;
                j++;
            }
        }
        return result;
    }
}
