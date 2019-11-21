package com.company;

// construct with a size
// size fixed and cannot grow
// rotate, and rotate effectively
// use generics
// support iteration

import java.util.Iterator;
import java.util.Objects;

public class CircularArray<T> implements Iterable<T> {
    private int start = 0;
    private int size = 0;
    private T[] array = null;

    public int getSize() {
        return size;
    }

    public CircularArray(int size) {
        this.size = size;
        this.array = (T[])(new Object[size]);
    }

    public void rotate(int index)  {
        start = getArrayIndex(index);
    }

    private int getArrayIndex(int index) {
        return (start + index) % size;
    }

    public T get(int index)  {
        return array[getArrayIndex(index)];
    }

    public void set(int index, T val)  {
        array[getArrayIndex(index)] = val;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularArrayIterator<T>(this);
    }
}

