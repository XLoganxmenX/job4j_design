package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RevertLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
        size++;
    }

    public boolean revert() {
        boolean rsl = size > 1;

        if (rsl) {
            Node<T> prevNode = null;
            Node<T> nextNode = null;
            Node<T> cursor = head;

            while (cursor != null) {
                nextNode = cursor.next;
                cursor.next = prevNode;
                prevNode = cursor;
                cursor = nextNode;
            }
            head = prevNode;
        }

        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}