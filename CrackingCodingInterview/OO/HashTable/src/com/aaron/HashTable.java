package com.aaron;


//requirements
//  value - generics
//  key - generics
//  add, remove through key
//  handle collision through linked list
//  generics with some hash function
//  insertion and return should be O(1) when there is no collision


import javax.swing.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.LinkedList;

class KeyValuePair<K extends Comparable<K>, V> {
    K key;
    V val;

    public KeyValuePair(K key, V val) {
        this.key = key;
        this.val = val;
    }
}

public class HashTable<K extends Comparable<K>, V> {
    private int tableSize = 100;
    private ArrayList<LinkedList<KeyValuePair>> array = null;

    public HashTable(int tableSize) {
        this.tableSize = tableSize;
        this.array = new ArrayList<>();
        for(int i = 0; i < tableSize; i++) {
            this.array.add(null);
        }

    }

    public int hashCode(K key) {
        return key.hashCode() % tableSize;
    }

    public void add(K key, V val) {
        int index = hashCode(key);
        KeyValuePair pair = new KeyValuePair(key, val);
        LinkedList<KeyValuePair> list = array.get(index);
        if(list == null) {
            list = new LinkedList<>();
        }
        list.add(pair);
        array.set(index, list);
    }

    public V remove(K key) {
        int index = hashCode(key);
        LinkedList<KeyValuePair> list = array.get(index);
        if(list == null) {
            return null;
        }
        KeyValuePair match = null;
        for(KeyValuePair pair: list) {
            if(pair.key.compareTo(key) == 0) {
                match = pair;
            }
        }
        if(match !=  null)
            list.remove(match);
        return (V)match.val;
    }

    public V get(K key) {
        int index = hashCode(key);
        LinkedList<KeyValuePair> list = array.get(index);
        if(list == null) {
            return null;
        }
        for(KeyValuePair pair: list) {
            if(pair.key.compareTo(key) == 0) {
                return (V)pair.val;
            }
        }
        return null;
    }
}
