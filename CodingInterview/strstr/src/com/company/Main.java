package com.company;

public class Main {
    public static int strstr(String termStr, String dataStr) {
        int retval  = -1;

        int termLength = termStr.length();
        int dataLength = dataStr.length();

        for (int i = 0; i < dataLength; i++) {
            int j = 0;
            for(j = 0; j < termLength ; j++) {
                if (i + j >= dataLength || termStr.charAt(j) != dataStr.charAt(i + j))
                    break;
            }
            if (j == termLength) {
                retval = i;
                return retval;
            }
        }
        return retval;
    }


    public static void main(String[] args) {
	// write your code here
        System.out.println(strstr("efg", "abcdefghij"));
        System.out.println(strstr("eg", "abcdefghij"));
        System.out.println(strstr("ab", "aab"));
        System.out.println(strstr("abcd", "aab"));

    }
}


