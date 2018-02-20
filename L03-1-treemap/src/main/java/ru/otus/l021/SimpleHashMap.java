package ru.otus.l021;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SimpleHashMap<K, V> implements Map<K, V> {
    private static final int INIT_CAPACITY = 16;
    private int storeCapacity = 0;
    private static final int MAX_STORE_CAPACITY = Integer.MAX_VALUE - 1;
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

        Node(K initKey, V initValue) {
            this.key = initKey;
            this.value = initValue;
        }
    }

    private void increaseStore() {
        if (MAX_STORE_CAPACITY - this.storeCapacity > INIT_CAPACITY) {
            this.storeCapacity += INIT_CAPACITY;
            this.store = Arrays.copyOf(this.store, this.storeCapacity);
        } else if(this.storeCapacity != MAX_STORE_CAPACITY) {
            this.storeCapacity = MAX_STORE_CAPACITY;
            this.store = Arrays.copyOf(this.store, this.storeCapacity);
        }
        distributionNode();
    }

    private void reduceStore() {
        if (this.storeCapacity > INIT_CAPACITY) {
            this.storeCapacity -= INIT_CAPACITY;
        }
        distributionNode();
        this.store = Arrays.copyOf(this.store, this.storeCapacity);
    }

    private void distributionNode() {
        Node<K, V> tempNode = null;
        for (Node<K, V> kvNode : store) {
            tempNode = kvNode;
            while (tempNode != null) {
                addNodeToStore(tempNode);
                tempNode = tempNode.next;
            }
        }
    }

    private void addNodeToStore(Node<K, V> n) {
        if (this.storeCapacity == 0) {
            this.store = (Node<K, V>[]) new Node[INIT_CAPACITY];
            this.storeCapacity = INIT_CAPACITY;
        }
        if (((double) this.size / this.storeCapacity) > this.scaleFactor) {
            increaseStore();
        }
        if (this.storeCapacity > INIT_CAPACITY
                && ((double) this.size / this.storeCapacity) < 1 - this.scaleFactor) {
            reduceStore();
        }
        int location = n.key.hashCode() % this.storeCapacity;
        if (this.store[location] == null) {
            this.store[location] = n;
        } else {
            addNodeLocation(this.store[location]).next = n;
        }

    }

    private Node<K,V> addNodeLocation(Node<K, V> n) {
        if (n.next == null) {
            return n;
        }
        return addNodeLocation(n.next);
    }

}
