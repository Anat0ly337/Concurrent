package queue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class CustomQueue<T> implements Queue<T> {
    private Object[] elements;

    private int count = 0;
    private final int size;

    public CustomQueue(int size) {
        this.size = size;
        this.elements = new Object[this.size];
    }

    @Override
    public synchronized boolean add(T t) {
        try {
            while (count == size) {
                System.out.println("Q full waiting.....");
                wait();
            }
            elements[count++] = t;
            Thread.sleep(1000);
            System.out.println("inserted " + t + " into Q by " + Thread.currentThread().getName());
            notify();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public synchronized T poll() {
        try {
            while (count == 0) {
                System.out.println("Q empty waiting.....");
                wait();
            }
            T temp = elementData(0);
            reorderArray();
            count--;
            Thread.sleep(1000);
            System.out.println("popped " + temp + " from the Q by " + Thread.currentThread().getName());
            notify();

            return temp;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) elements[index];
    }

    private void reorderArray() {
        elements = Arrays.copyOfRange(elements, 1, size + 1);
    }

    public void printQ() {
        System.out.println(Arrays.toString(elements));
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
