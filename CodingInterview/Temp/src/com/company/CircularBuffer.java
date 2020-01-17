package com.company;

import java.util.Arrays;

public class CircularBuffer {
    int max_capacity = 0;
    int length = 0;
    int start = 0;
    char[] data = null;

    public CircularBuffer(int max_capacity) {
        this.max_capacity = max_capacity;
        data = new char[max_capacity];
    }

    public boolean get(char[] out, int outputSize) {
        if(outputSize > length)
            return false;

        if(start + length > max_capacity && outputSize > max_capacity - start - length) {
            int part1Size = max_capacity - start;
            int part2Size = outputSize - part1Size;
            System.arraycopy(data, start, out, 0, part1Size);
            Arrays.fill(data, start, start + part1Size, (char) 0);
            System.arraycopy(data, 0, out, part1Size + 1, part2Size );
            Arrays.fill(data, 0, part2Size, (char) 0);
            start = part2Size + 1;
        } else
        {
            System.arraycopy(data, start, out, 0, outputSize);
            Arrays.fill(data, start, start + outputSize, (char) 0);
            start = start + outputSize;
        }
        length = length - outputSize;
        return true;
    }

    public boolean put(char[] in, int inputSize) {
        if(inputSize > max_capacity - length)
            return false;
        if(start + length + inputSize > max_capacity) {
            int part1Size = max_capacity - (start + length);
            int part2Size = inputSize - part1Size;
            System.arraycopy(in, 0, data, start + length, part1Size );
            System.arraycopy(in, part1Size + 1, data, 0, part2Size );
        } else {
            System.arraycopy(in, 0, data, start + length, inputSize );
        }
        length = length + inputSize;
        return true;
    }


}
