package aaron.javaramp;

//-----------------------------------------------------------------------------------------------------------------------------
// replace spaces in a string with %20
//-----------------------------------------------------------------------------------------------------------------------------



public class Main {

    public static void main(String[] args) {


    }
}

//-----------------------------------------------------------------------------------------------------------------------------
// write a method to decide if two strings are anagrams or not
//
//"rail safety" = "fairy tales"
//        "roast beef" = "eat for BSE" [3]
//        "debit card" = "bad credit"
//        "George Bush" = "he bugs Gore"
//        "Justin Timberlake" = "I’m a jerk but listen"
//        "New York Times" = "monkeys write"
//        "Church of Scientology" = "rich-chosen goofy cult"
//        "McDonald's restaurants" = "Uncle Sam's standard rot"
//-----------------------------------------------------------------------------------------------------------------------------

//public class Main {
//    public static boolean isAnagram(String str1, String str2) {
//        int[] charCount1 = new int[256];
//        int[] charCount2 = new int[256];
//
//        for(int i=0; i<str1.length(); i++){
//            charCount1[Character.toLowerCase(str1.charAt(i))] ++;
//        }
//        for(int i=0; i<str2.length(); i++){
//            charCount2[Character.toLowerCase(str2.charAt(i))] ++;
//        }
//
//        for (int i = 0; i<256; i++) {
//            if(charCount1[i] != charCount2[i]) {
//                if((char)i != ' ')
//                    return false;
//            }
//        }
//        return true;
//    }
//    public static void checkAndPrint(String str1, String str2) {
//        System.out.println("-------------");
//        System.out.println(str1);
//        System.out.println(str2);
//        System.out.println(isAnagram(str1, str2));
//    }
//    public static void main(String[] args) {
//        checkAndPrint( "roast beef", "eat for BSE");
//        checkAndPrint( "debit card", "bad credit");
//        checkAndPrint( "roast", "eat for BSE");
//        checkAndPrint( "debit card", "bad ");
//
//
//    }
//}


//-----------------------------------------------------------------------------------------------------------------------------
// remove dup characters in a string wo/ using additional buffer.
//-----------------------------------------------------------------------------------------------------------------------------
//
//public class Main {
//    //256 boolean cost, n time cost
//    public static String removeDup(String inputStr) {
//        char[] charArray = inputStr.toCharArray();
//        int length = inputStr.length();
//
//        // mark all dup as 0
//        boolean[] charExistArr = new boolean[256];
//        int offset =0;
//        for (int i = 0; i< length; i++)
//        {
//            boolean dup = false;
//            if(!charExistArr[charArray[i]]) {
//                charExistArr[charArray[i]] = true;
//            } else {
//                dup = true;
//            }
//
//            // move if not dup
//            if (dup) {
//                offset ++;
//            } else {
//                if (offset == 0) {
//                    continue;
//                }
//                charArray[i-offset] = charArray[i];
//            }
//        }
//        String returnStr = new String(charArray, 0, length - offset);
//        return returnStr;
//    }
//
//    //low space, n2 cost
//    public static String removeDup2(String inputStr) {
//        char[] charArray = inputStr.toCharArray();
//        int length = inputStr.length();
//
//        // mark all dup as 0
//        int offset =0;
//        for (int i = 0; i< length; i++)
//        {
//            // is it dup
//            boolean dup = false;
//            for (int j=0; j<i; j++)
//            {
//                if(charArray[i] == charArray[j]) {
//                    dup = true;
//                    break;
//                }
//            }
//
//            // move if not dup
//            if (dup) {
//                offset ++;
//            } else {
//                if (offset == 0) {
//                    continue;
//                }
//                charArray[i-offset] = charArray[i];
//            }
//        }
//        String returnStr = new String(charArray, 0, length - offset);
//        return returnStr;
//    }
//
//
////        // mark all dup as 0
////        for (int i = 0; i< length; i++)
////        {
////            for (int j=0; j<i; j++)
////            {
////                if(charArray[i] == charArray[j])
////                    charArray[i] = 0;
////            }
////        }
////
////        //move over
////        int offset =0;
////        for (int i=0; i< length; i++)
////        {
////            if (charArray[i] == 0) {
////                offset ++;
////            } else {
////                if (offset == 0) {
////                    continue;
////                }
////                charArray[i-offset] = charArray[i];
////            }
////        }
//
//
//
//    public static void main(String[] args) {
//
//
//        String inputStr = "aaacad";
//        System.out.println(removeDup(inputStr));
//
//        inputStr = "aba";
//        System.out.println(removeDup(inputStr));
//
//        inputStr = "";
//        System.out.println(removeDup(inputStr));
//
//        inputStr = "aaaa";
//        System.out.println(removeDup(inputStr));
//        inputStr = "aaaabbb";
//        System.out.println(removeDup(inputStr));
//    }
//}




//-----------------------------------------------------------------------------------------------------------------------------
// revert a c style string
//-----------------------------------------------------------------------------------------------------------------------------

//public class Main {
//
//    public static String reverse(String inputStr) {
//        char[] charArray = inputStr.toCharArray();
//        int length = inputStr.length();
//
//        for (int i = 0; i< length / 2; i++)
//        {
//            char temp = charArray[i];
//            charArray[i] = charArray[length - 1 - i];
//            charArray[length - 1 - i] = temp;
//        }
//
//        String returnStr = new String(charArray);
//        return returnStr;
//    }
//    public static void main(String[] args) {
//        String inputStr = "abcd";
//        System.out.println(reverse(inputStr));
//
//
//        inputStr = "abc";
//        System.out.println(reverse(inputStr));
//
//        inputStr = "";
//        System.out.println(reverse(inputStr));
//    }
//}
//
//

//-----------------------------------------------------------------------------------------------------------------------------
// String vs. string buffer
//-----------------------------------------------------------------------------------------------------------------------------
//First of all, String is immutable. Immutable in the sense of memory. It creates new objects every time you create strings or assign a new string/change the value. That's why it is advisable to be careful when using string
//Since String is immutable, str+= "" will cause a complete copy of the original content in str, hence the complexity is 1 + 2 + 3 + 4... = n x 2
//the alternative is to use String Buffer / String Builder
//StringBuffer is synchronized i.e. thread safe. It means two threads can't call the methods of StringBuffer simultaneously. it is hence less efficient
//StringBuilder is non-synchronized i.e. not thread safe. It means two threads can call the methods of StringBuilder simultaneously. it is hence more efficient



//public class Main {
//
//    public static void main(String[] args) {
//
//        long startTime = System.currentTimeMillis();
//
//        StringBuffer sb = new StringBuffer("Java");
//        for (int i=0; i<100000; i++){
//            sb.append("Tpoint");
//        }
//
//        System.out.println("Time taken by StringBuffer: " + (System.currentTimeMillis() - startTime) + "ms");
//
//        startTime = System.currentTimeMillis();
//        StringBuilder sb2 = new StringBuilder("Java");
//        for (int i=0; i<100000; i++){
//            sb2.append("Tpoint");
//        }
//        System.out.println("Time taken by StringBuilder: " + (System.currentTimeMillis() - startTime) + "ms");
//
//
//        startTime = System.currentTimeMillis();
//        String str = "Java";
//        for (int i=0; i<100000; i++){
//            str += "Tpoint";
//        }
//        System.out.println("Time taken by String: " + (System.currentTimeMillis() - startTime) + "ms");
//    }
//}


//-----------------------------------------------------------------------------------------------------------------------------
// Implement an algorithm to determine if a string has all unique characters What if you can not use additional data structures?
//-----------------------------------------------------------------------------------------------------------------------------

//import java.util.HashMap;
//import java.util.HashSet;
//
//class MyHashSet {
//    private char[] charArray;
//    private StringBuilder[] stringArray;
//
//    public MyHashSet() {
//        this.charArray = new char[256];
//        this.stringArray = new StringBuilder[256];
//    }
//
//    private int hash(char c) {
//        int a = (int)c;
////        System.out.println(a);
//        return a % 256;
//    }
//
//    public boolean add(char c) {
//        //hash to find the pos
//        int pos = hash(c);
//        //if empty, insert
//        if (charArray[pos] == 0)
//        {
//            charArray[pos] = c;
//            return true;
//        }
//        // if occupied and the same, return
//        else if (charArray[pos] == c)
//        {
//            return false;
//        }
//        // if occupied and not the same, it might be in the string buffer
//        else {
//            if (stringArray[pos] == null) {
//                stringArray[pos] = new StringBuilder();
//                stringArray[pos].append(c);
//                return true;
//            }
//            //check to see whether it is the string buffer
//            for (int i = 0; i<stringArray[pos].length(); i++)
//                if (stringArray[pos].charAt(i) == c)
//                    return false;
//            //attach to the end
//            stringArray[pos].append(c);
//            return true;
//        }
//    }
//}
//
//
//public class Main {
//
//    //solution using my own hashset
//    public static boolean allUniqueChar3(String str)
//    {
//        MyHashSet hashset = new MyHashSet();
//        for(int i = 0; i< str.length(); i++)
//        {
//            if (!hashset.add(str.charAt(i)))
//                return false;
//        }
//        return true;
//    }
//
//    //solution using brute force iteration
//    public static boolean allUniqueChar2(String str)
//    {
//        for(int i = 0; i< str.length(); i++)
//        {
//            for (int j=i+1; j<str.length(); j++)
//            {
//                if (str.charAt(i) ==str.charAt(j))
//                    return false;
//            }
//        }
//        return true;
//    }
//
//    //solution using Hashset, time efficient, space costly
//    public static boolean allUniqueChar(String str)
//    {
//        HashSet<Character> hashset = new HashSet<Character>();
//        for(int i = 0; i< str.length(); i++)
//        {
//            if (!hashset.add(str.charAt(i)))
//                return false;
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        char f = 'a';
//        char h = (char) (f + 256 * 2);
//
//        String a = "abcd";
//        String b = "aa";
//        String c = "abcdefghijklmn,opqb";
//        String d = "abcdefghijklmn,opqšɡ";
//        String e = "";
//        System.out.println(allUniqueChar3(a));
//        System.out.println(allUniqueChar3(b));
//        System.out.println(allUniqueChar3(c));
//        System.out.println(allUniqueChar3(d));
//        System.out.println(allUniqueChar3(e));
//
//    }
//}










