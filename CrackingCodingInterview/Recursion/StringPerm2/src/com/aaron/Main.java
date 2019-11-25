package com.aaron;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static class DupChar {
        char c = 0;
        int number = 0;

        public DupChar(char c, int number) {
            this.c = c;
            this.number = number;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < number; i++) {
                sb.append(c);
            }
            return sb.toString();
        }

        public char getChar() {
            return c;
        }

        public int getNumber() {
            return number;
        }
    }

    private static ArrayList<DupChar> getDupChar(String str) {
        ArrayList<DupChar> result = new ArrayList<>();
        HashMap<Character, Integer> hashmap = new HashMap<>();
        for(char c: str.toCharArray()) {
            if(!hashmap.containsKey(c)) {
                hashmap.put(c, 1);
            } else {
                int val = hashmap.get(c);
                val ++;
                hashmap.put(c, val);
            }
        }

        for (HashMap.Entry<Character, Integer> entry : hashmap.entrySet()) {
            Character c = entry.getKey();
            Integer val = entry.getValue();
            DupChar dupChar = new DupChar(c, val);
            result.add(dupChar);
        }
        return result;
    }

    private static ArrayList<int[]> _genPosTemplate(int numberofChar, int numberofPos, int originalNumberofPos) {
        ArrayList<int[]> result = new ArrayList<>();

        if(numberofPos == 1) {
            int[] arr = new int[originalNumberofPos];
            arr[originalNumberofPos - 1] = numberofChar;
            result.add(arr);
            return result;
        }

        if(numberofChar == 0) {
            int[] arr = new int[originalNumberofPos];
            result.add(arr);
            return result;
        }

        for(int i = 0; i <= numberofChar; i++) {
            ArrayList<int[]> temp = _genPosTemplate(numberofChar - i, numberofPos - 1, originalNumberofPos);
            for(int[] arr:temp) {
                arr[originalNumberofPos - numberofPos] = i;
            }
            result.addAll(temp);
        }

        return result;
    }

    private static ArrayList<String> _genString(char dupChar, String str, ArrayList<int[]> template) {
        if(template.size() == 0 || template.get(0).length != str.length() + 1)
            throw new IllegalArgumentException();
        ArrayList<String > result = new ArrayList<>();
        for(int[] arr: template) {
            StringBuilder sb = new StringBuilder(str);
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] != 0) {
                    char[] dupChars = new char[arr[i]];
                    for(int j = 0; j < arr[i]; j++) {
                        dupChars[j] = dupChar;
                    }
                    sb.insert(i, dupChars);
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    private static ArrayList<String> add(DupChar dupChar, ArrayList<String> perms) {
        if(perms == null || dupChar ==  null)
            return null;

        ArrayList<String> result = new ArrayList<>();

        if(perms.size() == 0) {
            result.add(dupChar.toString());
            return result;
        }

        int numberofChar = dupChar.getNumber();
        int numberofPos = perms.get(0).length() + 1;
        ArrayList<int[]> tempalte = _genPosTemplate(numberofChar, numberofPos, numberofPos);
        for(String str: perms) {
            result.addAll(_genString(dupChar.getChar(), str, tempalte));
        }
        return result;
    }

    private static ArrayList<String> getPerms(ArrayList<DupChar> dupChars) {
        ArrayList<String> result = null;

        if(dupChars.size() == 1) {
            result = new ArrayList<>();
            result.add(dupChars.get(0).toString());
            return result;
        }

        DupChar first = dupChars.remove(0);
        ArrayList<String> perms = getPerms(dupChars);
        result = add(first, perms);
        return result;
    }

    public static ArrayList<String> getPerms(String str) {
        ArrayList<DupChar> dupChars = getDupChar(str);
        return getPerms(dupChars);
    }

    public static void main(String[] args) {
//        ArrayList<int[]> arr = _genPosTemplate(3, 5, 5);
//        for(int[] item: arr) {
//            StringBuilder sb = new StringBuilder();
//            for(int i = 0; i < item.length; i++) {
//                sb.append(item[i]);
//            }
//            System.out.println(sb);
//        }
//        ArrayList<String> strs = _genString('a', "bcde", arr);
//        for(String str: strs) {
//            System.out.println(str);
//        }

        ArrayList<String> results = getPerms("aabbcd");
        System.out.println(results.size());
        System.out.println(results);


    }
}
