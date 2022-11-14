package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void widen() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        }
        container = Arrays.copyOf(this.container, this.container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length - 1) {
            widen();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T nextValue = container[index];
        container[index] = newValue;
        return nextValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T prevValue = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return prevValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int iteratorCursor = 0;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorCursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[iteratorCursor++];
            }
        };
    }
}
