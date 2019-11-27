package com.aaron;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;


public class Main {

    public static String sortChar(String str) {
        char tempArray[] = str.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static ArrayList<String> groupAnagram(ArrayList<String> strs) {

        ArrayList<String> results = new ArrayList<>();
        HashMap<String, ArrayList<String>> hashmap = new HashMap<>();
        for(String str: strs) {
            String key = sortChar(str);
            if(hashmap.containsKey(key)) {
                hashmap.get(key).add(str);
            } else {
                ArrayList<String> anagrams = new ArrayList<>();
                anagrams.add(str);
                hashmap.put(key, anagrams);
            }
        }

        TreeMap<String, ArrayList<String>> treemap = new TreeMap<>();
        treemap.putAll(hashmap);

        for(ArrayList<String> arr: treemap.values()) {
            results.addAll(arr);
        }

        return results;
    }

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        input.add("tap");
        input.add("listen");
        input.add("hello");
        input.add("pat");
        input.add("silent");
        ArrayList<String> result = groupAnagram(input);
        System.out.println(result);



    }
}
