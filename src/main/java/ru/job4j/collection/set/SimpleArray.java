package ru.job4j.collection.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] elements;
    private int size;
    private int count = 0;


    public SimpleArray(int size) {
        this.size = size;
        elements = (T[]) new Object[size];
    }

    public void add(T model) {
        elements[count++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, count);
        elements[index] = model;

    }

    public int size() {
        return count;
    }

    public void remove(int index) {
        Objects.checkIndex(index, count);
        System.arraycopy(elements, index, elements, index - 1,  size - index - 1);
        count--;
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return elements[index];
    }
    @Override
    public Iterator<T> iterator() {

        Iterator<T> it = new Iterator<T>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];
            }
        };
        return it;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return size == that.size && count == that.count && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, count);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

}
