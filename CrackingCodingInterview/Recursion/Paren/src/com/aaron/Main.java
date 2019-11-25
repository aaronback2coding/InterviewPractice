package com.aaron;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    enum Paren {
        LEFT,
        RIGHT
    }

    private static String genStr(Paren paren, int number) {
        char c = paren == Paren.LEFT ? '(' : ')';
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < number; i++){
            sb.append(c);
        }
        return sb.toString();
    }

    public static void getParens(String prefix, int leftInPrefix, int rightInPrefix, int leftRemaining, int rightRemaining, ArrayList<String> results) {
        if(leftRemaining == 0) {
            String str = genStr(Paren.RIGHT, rightRemaining);
            str = prefix + str;
            results.add(str);
            return;
        }

        getParens(prefix + '(',
                leftInPrefix + 1,
                rightInPrefix,
                leftRemaining - 1,
                rightRemaining,
                results);
        if(leftInPrefix > rightInPrefix) {
            getParens(prefix + ')',
                    leftInPrefix,
                    rightInPrefix + 1,
                    leftRemaining,
                    rightRemaining - 1,
                    results);
        }

    }

    public static ArrayList<String> getParens(int n) {
        ArrayList<String> results = new ArrayList<>();
        getParens("", 0, 0, n, n, results);
        return results;
    }

    public static void main(String[] args) {
	// write your code here
        ArrayList<String> results = getParens(4);
        for(String str: results) {
            System.out.println(str);
        }
    }
}
