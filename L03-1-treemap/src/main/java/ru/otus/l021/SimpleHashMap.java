package ru.otus.l021;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SimpleHashMap<K, V> implements Map<K, V> {
    private static final int INIT_CAPASITY = 16;
    private int storeCapasity = INIT_CAPASITY;
    private static final int MAX_STORE_CAPASITY = Integer.MAX_VALUE - 1;
    private double scaleFactor = 0.75;
    private int size = 0;

    private Node<K,V>[] store;



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
    public V put(K key, V value) {
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
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K initKey, V initValue, Node<K, V> initNext) {

        }
    }

}
