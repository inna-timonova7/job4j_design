package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = putForNull(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity  *= 2;
        MapEntry<K, V>[] newTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : newTable) {
            if (entry != null) {
                table[putForNull(entry.key)] = entry;
                newTable = table;
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = putForNull(key);
        if (table[index] != null && checkKey(table[index].key, key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = putForNull(key);
        if (table[index] != null
                && checkKey(table[index].key, key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    private int putForNull(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean checkKey(K entryKey, K key) {
        return Objects.hashCode(entryKey) == (Objects.hashCode(key))
                && Objects.equals(entryKey, key);
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
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
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
    }
}
