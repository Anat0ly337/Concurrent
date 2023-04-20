package org.example.cme;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ThreadSafeMap<K, V>
    implements Map<K, V>, Cloneable, Serializable {

    private final Object lock = new Object();
    private CustomSet<V> customSet = new CustomSet<>();

    @Override
    public V put(K key, V value) {
        synchronized (lock) {
            customSet.add(value);
        }
        return value;
    }

    @Override
    public Collection<V> values() {
        return customSet;
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<>();
    }

    @Override
    public int size() {
        return customSet.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
