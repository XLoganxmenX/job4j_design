package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index = -1;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int i = index + 1;
        while (i < data.length && data[i] == null) {
            i++;
        }
        return i < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (index + 1 < data.length && data[index + 1] == null) {
            index++;
        }
        return data[++index];
    }
}
