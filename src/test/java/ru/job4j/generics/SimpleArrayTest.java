package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenAddAndGetInArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddException() {
        SimpleArray<Integer> test = new SimpleArray<>(1);
        test.add(1);
        test.add(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.get(0);
    }

    @Test
    public void whenSetInArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.set(0, 2);
        assertThat(simpleArray.get(0), is(2));
    }

    @Test
    public void whenRemoveMiddleElemInArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.remove(1);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(3));
        assertThat(simpleArray.get(2), is(4));
    }

    @Test
    public void whenRemoveSingleElemInArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.remove(0);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenMultiHasNextThenTrue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(1);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenReadTwoInArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextInEmptyArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        Iterator<Integer> it = simpleArray.iterator();
        it.next();
        it.next();
    }
}