package com.company;

public class Main {

    public static void main(String[] args) {
        int size = 10;
        CircularArray<Integer> ca = new CircularArray<>(size);
        for(int i = 0; i < size; i++) {
            ca.set(i, i);
        }
        for(Integer i: ca) {
            System.out.println(i);
        }
    }
}
