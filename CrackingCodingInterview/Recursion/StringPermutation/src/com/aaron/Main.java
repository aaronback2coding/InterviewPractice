package com.aaron;

import java.util.ArrayList;

public class Main {

    // solution using recursion
    private static ArrayList<String> add(String permutation, String str) {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i<= permutation.length(); i++) {
            StringBuilder sb = new StringBuilder(permutation);
            sb.insert(i, str);
            result.add(sb.toString());
        }
        return result;
    }

    private static ArrayList<String> add(ArrayList<String> permutations, String str) {
        ArrayList<String> result = new ArrayList<>();
        for(String permutation:permutations) {
            result.addAll(add(permutation, str));
        }
        return result;
    }

    public static ArrayList<String> getPermutation(String str) {
        if(str.length() == 0) {
            return null;
        }
        if(str.length() == 1) {
            ArrayList<String> result = new ArrayList<>();
            result.add(str);
            return result;
        }

        String substr = str.substring(0, str.length() - 1);
        String endstr = str.substring(str.length() - 1, str.length());
        ArrayList<String> substringPermutations = getPermutation(substr);
        ArrayList<String> result = add(substringPermutations, endstr);
        return result;
    }

    public static ArrayList<String> getPermutation2(String str) {
        if(str.length() == 0) {
            return null;
        }
        if(str.length() == 1) {
            ArrayList<String> result = new ArrayList<>();
            result.add(str);
            return result;
        }

        String substr = str.substring(0, str.length() - 1);
        String endstr = str.substring(str.length() - 1, str.length());
        ArrayList<String> substringPermutations = getPermutation(substr);
        ArrayList<String> result = add(substringPermutations, endstr);
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> results = getPermutation("abcd");
        System.out.println(results.size());
        System.out.println(results);

    }
}
