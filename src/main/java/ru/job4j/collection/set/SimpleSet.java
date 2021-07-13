package ru.job4j.collection.set;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>(3);

    @Override
    public boolean add(T value) {

        if (contains(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        if (set.size() == 0) {
            return false;
        }
        Iterator<T> iterator = set.iterator();
        while (iterator().hasNext()) {
            if (Objects.equals(iterator().next(), value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
