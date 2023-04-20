package org.example.cme;

import java.util.AbstractCollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomSet<E> extends AbstractCollection<E> {
    private int cursor;
    private Object[] array = new Object[0];

    public CustomSet() {
    }

    @Override
    public Iterator<E> iterator() {
        return new ValueIterator();
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean add(E e) {
        int size = array.length + 1;
        array = new Object[size];
        array[size -1] = e;
        return true;
    }

    final class ValueIterator implements Iterator<E> {
        @Override
        public boolean hasNext() {
            return cursor < array.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No further elements");
            }
            return (E) array[cursor++];
        }
    }
}
