package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;

    private int modCount = 0;

    private Node<E> node;

    public SimpleLinkedList() {
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        size++;
        modCount++;

        Node<E> element = new Node<>(value, null);
        if (node == null) {
            node = element;
            return;
        }

        Node<E> tail = node;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = element;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int cursor = 0;
        Node<E> elem = node;
        while (cursor < index) {
            elem = elem.next;
            cursor++;
        }
        return elem.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private Node<E> tail = node;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return tail != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = tail.value;
                tail = tail.next;
                return value;
            }
        };
    }
}
