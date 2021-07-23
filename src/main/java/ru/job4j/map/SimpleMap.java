package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private int length = 16;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public SimpleMap() {
        capacity = (int) (LOAD_FACTOR * length);
        table = new MapEntry[length];
    }

    @Override
    public boolean put(K key, V value) {
        if (count > capacity) {
            expand();
        }
        int index = indexFor(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        MapEntry<K, V> elem = table[index];
        if (!elem.getKey().equals(key)) {
            return false;
        }
        elem.setValue(value);
        return true;
    }

    private int hash(int hashCode) {
        return (hashCode()) ^ (hashCode >>> 16);
    }

    private int indexFor(K key) {
        int hash = 31;
        hash = hash + key.hashCode();
        return hash % table.length;
    }

    private void expand() {
        MapEntry<K, V>[] newTable = table;
        MapEntry<K, V> element;
        modCount++;
        length = (length * 3) / 2;
        capacity = (int) (length * LOAD_FACTOR);
        table = new MapEntry[length];
        count = 0;
        for (var el : newTable) {
            if (el != null) {
                element = el;
                this.put(element.getKey(), element.getValue());
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(key);
        if (table[index] == null
                || !(table[index].getKey().equals(key))) {
            return null;
        }
        return table[indexFor(key)].getValue();
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(key);
        if (table[index] == null || !(table[index].getKey().equals(key))) {
            return false;
        }
        table[index] = null;
        modCount++;
        count--;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final MapEntry<K, V>[] tempTable = table;
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[point] != null && point < table.length - 1) {
                    point++;
                }
                return table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return tempTable[point++].getKey();
            }
        };
    }

    private static class MapEntry<K, V> {

        private final K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}