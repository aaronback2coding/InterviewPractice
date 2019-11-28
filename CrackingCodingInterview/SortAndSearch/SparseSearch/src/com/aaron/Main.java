package com.aaron;

// learning - testing needs to be thorough.

public class Main {


    public static int search(String[] strs, String str,  int start, int end) {
        if(start == end) {
            if(strs[start].compareTo(str) == 0)
                return start;
            return -1;
        }

        int mid = (start + end) / 2;
        while(mid >start && strs[mid].compareTo("") == 0) {
            mid --;
        }

        int compareResult = strs[mid].compareTo(str);
        if(compareResult == 0)
            return mid;
        if(compareResult > 0) {
            return search(strs, str, start, mid);
        }
        if(compareResult < 0) {
            return search(strs, str, mid + 1, end);
        }
        return -1;

    }

    public static int search(String[] strs, String str) {
        return search(strs, str, 0, strs.length - 1);
    }

    public static void main(String[] args) {
        String[] stringList = {"apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower"};
        System.out.println(search(stringList, "carrot"));
        for (String s : stringList) {
        	String cloned = new String(s);
        	System.out.println("<" + cloned + "> " + " appears at location " + search(stringList, cloned));
        }
    }
}


