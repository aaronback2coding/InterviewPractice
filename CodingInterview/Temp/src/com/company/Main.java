package com.company;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    void listPatternHelper(String prefix, String suffix, ArrayList<String> results) {
        // base case
        if(suffix == "") {
            if(exists(prefix))
                results.add(prefix);
            return;
        }

        // recursion
       String[] strs = suffix.split("\\*");
       String newPrefix = prefix + strs[0];
       String newSuffix = strs.length == 2 ? strs[1] : "";

       ArrayList<String> paths = listPath(newPrefix);
       for(String path : paths) {
           listPatternHelper(path, newSuffix, results);
       }
    }


    ArrayList<String> listPattern(String pattern) {
        ArrayList<String> results = new ArrayList<>();
        listPatternHelper("", pattern, results);
        return results;
    }
    ArrayList<String> listPath(String path) {
        return null;
    }

    boolean exists(String path) {
        return false;
    }

    public static void main(String[] args) {

        String a = "/b";
        String[] strs = a.split("\\*");



    }
}
