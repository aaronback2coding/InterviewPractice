package com.aaron;

import java.util.ArrayList;
import java.util.HashSet;

// learning - on the surface, p(n) = p(n/2) + p(n/2) is O(log(n)) and therefore more efficient than p(n) = p(n-1) + 1, which is
//  O(n). this is true in cases where p(n/2) + p(n/2)'s complexity is as same as p(n-1) + 1, but in this case, the first one is
//  actually more complex thant the second one. As both of them in the end are decided by the number of possible subset combinations
//  so their complexity is actually the same, which is n * 2 power by n.

public class Main<T> {


    public static<T> ArrayList<HashSet<T>> getSubset(HashSet<T> set) {
        if(set == null)
            return null;
        ArrayList<HashSet<T>> result = new ArrayList<>();

        if(set.size() <= 1) {
            result.add(set);
            return result;
        }

        // split into two sets
        HashSet<T> set1 = new HashSet<T>();
        HashSet<T> set2 = new HashSet<T>();
        int i = 0;
        for(T item: set) {
            if(i%2 == 0)
                set1.add(item);
            else
                set2.add(item);
            i++;
        }

        // get subsset from each half and merge them together
        ArrayList<HashSet<T>> result1 = getSubset(set1);
        ArrayList<HashSet<T>> result2 = getSubset(set2);
        result.addAll(result1);
        result.addAll(result2);
        for(HashSet<T> subset1: result1) {
            for(HashSet<T> subset2: result2) {
                HashSet<T> mergedSubset = new HashSet<>();
                mergedSubset.addAll(subset1);
                mergedSubset.addAll(subset2);
                result.add(mergedSubset);
            }
        }
        return result;

    }

    public static void main(String[] args) {
	// write your code here
        HashSet<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");
        set.add("f");
        set.add("g");

        ArrayList<HashSet<String>> subsets = getSubset(set);
        if(subsets == null) {
            return;
        }
        System.out.println(subsets.size());
        for(HashSet<String> subset: subsets) {
            System.out.println(subset);
        }
    }
}
