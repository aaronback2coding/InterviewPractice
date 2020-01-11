package com.company;

public class Main {
    public static int strstr(String termStr, String dataStr) {

        int resultPose = -1;
        int curTermStr = 0;
        for(int curDataStr = 0; curDataStr < dataStr.length(); curDataStr++) {
            if(curTermStr == termStr.length()) {
                resultPose = curDataStr - curTermStr;
                return resultPose;
            }
            if(dataStr.charAt(curDataStr) != termStr.charAt(curTermStr)) {
                curTermStr = 0;
                continue;
            } else {
                curTermStr ++;
            }
        }
        return resultPose;

    }

    public static void main(String[] args) {
	// write your code here
        System.out.println(strstr("efg", "abcdefghij"));
        System.out.println(strstr("eg", "abcdefghij"));
        System.out.println(strstr("abc", "abcdefghij"));

    }
}
