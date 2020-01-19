package com.company;

public class Heap {
    int[] data;
    int capacity;
    int size;

    public Heap(int[] data, int capacity) {
        if(data.length >= capacity)
            return;
        this.data = new int[capacity];
        System.arraycopy(data, 0, this.data, 0, data.length);
        this.capacity = capacity;
        this.size = data.length;

        //initialize of the heap
        for(int i = size / 2; i >= 0; i--) {
            heapify(i - 1);
        }
    }

    public void add(int val) {
        if(size == capacity)
            return;
        size ++;
        int i = size - 1;
        int parent = (i + 1) / 2 - 1;
        while(parent >= 0 && data[parent] > data[i])  {
            swap(parent, i);
            i = parent;
            parent = (i + 1) / 2 - 1;
        }
    }

    public int remove() {
        if(size == 0)
            throw new RuntimeException();
        int result = data[0];
        swap(0, size - 1);
        size --;
        heapify(0);
        return result;
    }

    void swap (int i, int j) {
        int temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }

    void heapify(int i) {
        if(i < 0 || i >= size)
            return;

        int left = (i + 1) * 2  - 1;
        int right = left + 1;
        int smallest;

        if(left < size && data[left] < data[i])
            smallest = left;
        else
            smallest = i;

        if(right < size && data[right] < data[smallest])
            smallest = right;

        if(smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }


}
