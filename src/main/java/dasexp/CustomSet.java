package dasexp;

import java.util.AbstractCollection;

import java.util.Arrays;
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
        Object[] tmp = array;
        array = new Object[size];
        for (int i = 0; i < tmp.length ; i++) {
            array[i] = tmp[i]; //fill elements
        }
        array[size -1] = e;
        return true;
    }

    @Override
    public Object[] toArray() {
        return array;
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
