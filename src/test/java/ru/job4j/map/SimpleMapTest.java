package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenPutThenGet() {
        SimpleMap<Integer, String> table = new SimpleMap<>();
        table.put(1, "One");
        table.put(2, "Two");
        table.put(3, "Three");
        assertThat(table.get(1), is("One"));
        assertThat(table.get(2), is("Two"));
        assertThat(table.get(3), is("Three"));
    }

    @Test
    public void whenPutThenGetEmpty() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        table.put(1, 1);
        table.put(3, 3);
        assertThat(table.get(1), is(1));
        assertNull(table.get(2));
    }

    @Test
    public void whenPutThenDelete() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        table.put(1, 1);
        table.put(3, 3);
        assertThat(table.get(1), is(1));
        assertTrue(table.remove(1));
        assertNull(table.get(1));
    }

    @Test
    public void whenDelete() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        assertFalse(table.remove(0));
    }

    @Test
    public void whenPutThenReplace() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        table.put(1, 1);
        assertTrue(table.put(1, 3));
        assertThat(table.get(1), is(3));
    }

    @Test
    public void whenPutThenReplaceSame() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        assertTrue(table.put(2, 1));
        assertTrue(table.put(2, 1));
    }

    @Test
    public void whenTableExpand() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        for (int i = 0; i < 20; i++) {
            table.put(i, i);
        }
        for (int i = 0; i < 20; i++) {
            assertThat(table.get(i), is(i));
        }
    }

    @Test
    public void whenHasNotNext() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        Iterator<Integer> it = table.iterator();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorExceptionWhenEmpty() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        Iterator<Integer> it = table.iterator();
        it.next();
    }
}