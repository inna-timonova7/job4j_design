package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    private int index = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void widen() {
        this.container = Arrays.copyOf(this.container, this.container.length * 2);
    }

    @Override
    public void add(T value) {
        if (index > container.length - 1) {
            widen();
        }
        container[index++] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T nextValue = container[index];
        if (index < this.index) {
            container[index] = newValue;
            return nextValue;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T prevValue = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[--size] = null;
        modCount++;
        return prevValue;
    }

    @Override
    public T get(int index) {
        if (index < 0 || size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.container[index];
    }

    @Override
    public int size() {
        if (size < 0) {
            throw new IllegalArgumentException();
        } else {
            container = (T[]) new Object[size];
            return container.length;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int iteratorCursor = 0;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return iteratorCursor < index;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[iteratorCursor++];
            }
        };
    }
}
