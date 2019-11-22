// a hash table uses chainning (linked list) to handle collision



package com.aaron;

public class Main {

    public static void main(String[] args) {
        HashTable<String, Integer> hashtable = new HashTable<>(100);

        for(Integer i = 0; i < 1000; i++) {
            String str = i.toString();
            hashtable.add(str, i);
        }

        System.out.println(hashtable.get("1"));
        System.out.println(hashtable.get("10"));
        System.out.println(hashtable.get("100"));
        System.out.println(hashtable.get("1000"));

    }
}
