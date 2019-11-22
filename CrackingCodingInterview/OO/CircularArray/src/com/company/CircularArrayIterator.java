package com.company;

import java.util.Iterator;

public class CircularArrayIterator<T> implements Iterator<T> {
    private CircularArray<T> ca;
    private int cur = 0;

    public CircularArrayIterator(CircularArray<T> ca) {
        this.ca = ca;
    }

    @Override
    public boolean hasNext() {
        return cur < ca.getSize();
    }

    @Override
    public T next() {
        T val = ca.get(cur);
        cur++;
        return val;
    }
}
