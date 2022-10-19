package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        index = nextElement(0);
    }

    @Override
    public boolean hasNext() {
        return index != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer elements = data[index++];
        index = nextElement(index);
        return elements;
    }

    private int nextElement(int idx) {
        int rsl = -1;
        for (int i = idx; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
}