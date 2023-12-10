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
        return findEvenIndex() > index;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = findEvenIndex();

        return data[index];
    }

    private int findEvenIndex() {
        int evenIndex = index;
        for (int i = index + 1; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                evenIndex = i;
                break;
            }
        }
        return evenIndex;
    }

}
