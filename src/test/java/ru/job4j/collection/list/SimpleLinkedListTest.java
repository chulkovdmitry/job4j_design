package ru.job4j.collection.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

    @Test
    public void whenAddAndGet() {
        list.add(11);
        list.add(544);
        list.add(33);
        assertThat(list.get(2), is(33));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBounds() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.get(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        list.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        list.add(33);
        Iterator<Integer> it = list.iterator();
        list.add(17);
        it.next();
    }
}