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

    private Node<K, V>[] store;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        this.store = (Node<K, V>[]) new Node[INIT_CAPACITY];
        this.storeCapacity = INIT_CAPACITY;
    }

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
        return key != null && findNodeByKey(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return value != null && findNodeByValue(value) != null;
    }

    @Override
    public V get(Object key) {
        Node<K, V> tempNode = findNodeByKey(key);
        if (tempNode == null) {
            return null;
        }
        return tempNode.value;
    }

    @Override
    public V put(K key, V value) {
        addNodeToStore(new Node<>(key, value));
        return value;
    }

    @Override
    public V remove(Object key) {
        return deleteKeyToStore(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e: m.entrySet()) {
           addNodeToStore(new Node<>(e.getKey(), e.getValue()));
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.store = (Node<K, V>[]) new Node[INIT_CAPACITY];
        this.storeCapacity = INIT_CAPACITY;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("entrySet()");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("entrySet()");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("entrySet()");
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(K initKey, V initValue) {
            this.hash = initKey.hashCode();
            this.key = initKey;
            this.value = initValue;
        }
    }

    private Node<K, V> findNodeByKey(Object k) {
        if (size == 0 && k == null) {
            return null;
        }
        int location = k.hashCode() % this.storeCapacity;
        if (this.store[location] != null && this.store[location].key.equals(k)) {
            return this.store[location];
        }
        Node<K, V> tempNode = this.store[location];
        while (tempNode.next != null) {
            if (tempNode.next.key.equals(k)) {
                return tempNode;
            }
            tempNode = tempNode.next;
        }
        return null;
    }

    private Node<K, V> findNodeByValue(Object v) {
        if (size == 0 && v == null) {
            return null;
        }
        Node<K, V> tempNode = null;
        for (int i = 0; i < this.storeCapacity; i++) {
            if (this.store[i] == null) {
                continue;
            }
            tempNode = this.store[i];
            if (tempNode.value == v || tempNode.value.equals(v)) {
                return tempNode;
            }
            while (tempNode.next != null) {
                if (tempNode.next.value == v || tempNode.next.value.equals(v)) {
                    return tempNode.next;
                }
                tempNode = tempNode.next;
            }
        }
        return null;
    }

    private V deleteKeyToStore(Object key) {
        Node<K, V> tempNode = null;
        for (int i = 0; i < this.storeCapacity; i++) {
            if (this.store[i] == null) {
                continue;
            }
            tempNode = this.store[i];
            if (tempNode.key == key || tempNode.key.equals(key)) {
                this.store[i] = tempNode.next;
                this.size--;
                return tempNode.value;
            }
            while (tempNode.next != null) {
                if (tempNode.next.key == key || tempNode.next.key.equals(key)) {
                    V v = tempNode.next.value;
                    tempNode.next = tempNode.next.next;
                    this.size--;
                    return v;
                }
                tempNode = tempNode.next;
            }
        }

        if (this.storeCapacity > INIT_CAPACITY
                && ((double) this.size / this.storeCapacity) < 1 - this.scaleFactor) {
            reduceStore();
        }
        return null;
    }


    private void addNodeToStore(Node<K, V> n) {
        if (((double) this.size / this.storeCapacity) > this.scaleFactor) {
            increaseStore();
        }
        int location = n.hash % this.storeCapacity;
        addNodeLocation(n, location);
        this.size++;
    }

    private void addNodeLocation(Node<K, V> kvNode, int addLocation) {
        if (this.store[addLocation] == null || this.store[addLocation].key.equals(kvNode.key) ) {
            kvNode.next = this.store[addLocation].next;
            this.store[addLocation] = kvNode;
            return;
        }
        Node<K, V> nextNode = this.store[addLocation];
        while (true) {
            if (nextNode.next == null) {
                nextNode.next = kvNode;
                return;
            }
            if (nextNode.next.key.equals(kvNode.key)) {
                kvNode.next = nextNode.next.next;
                nextNode.next = kvNode;
                return;
            }
            nextNode = nextNode.next;
        }
    }

    private void increaseStore() {
        if (MAX_STORE_CAPACITY - this.storeCapacity > INIT_CAPACITY) {
            this.storeCapacity += INIT_CAPACITY;
            this.store = Arrays.copyOf(this.store, this.storeCapacity);
        } else if (this.storeCapacity != MAX_STORE_CAPACITY) {
            this.storeCapacity = MAX_STORE_CAPACITY;
            this.store = Arrays.copyOf(this.store, this.storeCapacity);
        }
        distributionNode();
    }

    private void reduceStore() {
        if (this.storeCapacity > INIT_CAPACITY) {
            this.storeCapacity -= INIT_CAPACITY;
            distributionNode();
            this.store = Arrays.copyOf(this.store, this.storeCapacity);
        }
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

}
