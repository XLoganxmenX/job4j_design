package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int i = index + 1;
        while (i < data.length && data[i] % 2 != 0) {
            i++;
        }
        return i < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (index + 1 < data.length && data[index + 1] % 2 != 0) {
            index++;
        }
        return data[++index];
    }
}
