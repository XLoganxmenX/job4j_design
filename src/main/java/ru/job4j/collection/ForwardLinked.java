package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);

        Node<T> node = head;
        if (size == 0) {
            head = newNode;
        } else {
            for (int i = 0; i < size - 1; i++) {
                node = node.next;
            }
            node.next = newNode;
        }

        size++;
        modCount++;
    }

    public void addFirst(T value) {
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            head = new Node<>(value, head);
        }

        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.item;
    }

    public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<T> delNode = head;
        head = head.next;

        delNode.next = null;
        T delVal = delNode.item;
        delNode.item = null;

        size--;
        return delVal;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private Node<T> cursor = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return cursor != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T returnNodeNum = cursor.item;
                cursor = cursor.next;

                return returnNodeNum;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
