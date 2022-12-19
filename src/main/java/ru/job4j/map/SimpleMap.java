package ru.job4j.map;

import ru.job4j.generics.Node;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private final int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private final float threshold = table.length * LOAD_FACTOR;

    @Override
    public boolean put(K key, V value) {
        int capacity;
        boolean result = false;
        if (count >= Math.round(threshold)) {
            expand();
        }
        int index = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        if (count >= Math.round(threshold)) {
            MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
            for (MapEntry<K, V> old : table) {
                if (old != null) {
                    MapEntry<K, V> mapEntry = (MapEntry<K, V>) old;
                    int newIndex = (old.key == null) ? 0 : indexFor(hash(old.key.hashCode()));
                    newTable[newIndex] = old;
                    count++;
                }
            }
            table = newTable;
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null
                && Objects.equals(key, table[index].key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null
                && Objects.equals(key, table[index].key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length - 1 && table[cursor] == null) {
                    cursor++;
                }
                if (cursor >= table.length) {
                    return false;
                }
                return table[cursor] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getKey() {
            return (V) key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            MapEntry<?, ?> node = (MapEntry<?, ?>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }
}