package aaron.javaramp;

//problem statement
//input: whatisjava
//input: what, is, java
//output: what is java
//
//solution flow
//init dictionary - using a set
//init input
//check till the end


import java.util.HashSet;
import java.util.Set;


class StringParser {
    String resultStr = "";
    String inputStr = "";
    Set<String> dictionary;

    public StringParser (String inputStr, Set<String> dictionary) {
        this.inputStr = inputStr;
        this.dictionary = dictionary;
    };

    public boolean injectSpace() {

        if (inputStr == "") {
            return false;
        }
        resultStr = "";

        int curStart = 0;
        int curEnd = curStart;
        boolean reachEnd = false;

        while(!reachEnd)
        {
            String temp = inputStr.substring(curStart, curEnd);
            System.out.println(temp);
            if (dictionary.contains(temp)) {
                resultStr += (temp + " ");
                curStart = curEnd;
                curEnd = curStart;
                if(curStart >= inputStr.length()) {
                    return true;
                }

            } else {
                curEnd++;
                if(curEnd > inputStr.length()) {
                    reachEnd = true;
                    return false;
                }
            }

        }
        return false;
    }


}



public class Main {

    public static void main(String[] args) {

        String inputStr = "WhatisJava";

        Set<String> dictionary = new HashSet<String>();
        dictionary.add("What");
        dictionary.add("is");
        dictionary.add("Java");

        StringParser parser = new StringParser(inputStr, dictionary);
        if(parser.injectSpace()) {
            System.out.println(parser.resultStr);
        }







    }
}
