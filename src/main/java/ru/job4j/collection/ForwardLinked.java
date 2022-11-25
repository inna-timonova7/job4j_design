package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        T ref = head.value;
        Node<T> temp = head;
        head = head.next;
        temp.value = null;
        temp.next = null;
        return ref;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    public boolean revert() {
        boolean rsl = false;
        if (head == null || head.next == null) {
            return rsl;
        }
        Node<T> current = head.next;
        head.next = null;
        while (current != null) {
            Node<T> next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        rsl = true;
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

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
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
