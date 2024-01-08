package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((count * 1.0f / capacity) >= LOAD_FACTOR) {
            expand();
        }

        int index = indexByKey(key);
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }

        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexByKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int newIndex = indexByKey(table[i].key);
                newTable[newIndex] = table[i];
            }
        }

        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;

        if (contains(key)) {
            value = table[indexByKey(key)].value;
        }

        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean isRemove = false;

        if (contains(key)) {
                table[indexByKey(key)] = null;
                isRemove = true;
                count--;
                modCount++;
            }

        return isRemove;
    }

    private boolean contains(K key) {
        int index = indexByKey(key);
        boolean isContains = false;

        if (table[index] != null) {
            MapEntry<K, V> node = table[index];
            if ((Objects.hashCode(key) == Objects.hashCode(node.key))
                    && Objects.equals(key, node.key)) {
                isContains = true;
            }
        }

        return isContains;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}