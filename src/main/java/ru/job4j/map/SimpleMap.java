package ru.job4j.map;

import ru.job4j.generics.Node;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private float threshold = table.length * LOAD_FACTOR;

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count >= Math.round(threshold)) {
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
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        for (MapEntry<K, V> old : table) {
            if (old != null) {
                int newIndex = (old.key == null) ? 0 : indexFor(old.key.hashCode()
                        & (newTable.length - 1));
                newTable[newIndex] = old;
            }
        }
        table = newTable;
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
        int result = 0;
        if (key != null) {
            result = indexFor(hash(key.hashCode()));
        } else {
            result = 0;
        }
        return result;
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
