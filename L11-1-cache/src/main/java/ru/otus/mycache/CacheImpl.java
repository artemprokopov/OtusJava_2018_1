package ru.otus.mycache;

import java.util.Map;
import java.util.WeakHashMap;

public class CacheImpl implements ICache {
    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<KeyCache, CacheElement> elements = new WeakHashMap<>();
    @Override
    public <K, V> void put(K key, V value) {
        CacheElement<K, V> cacheElement = new CacheElementImpl<>(key, value, idleTimeMs, lifeTimeMs);
        elements.put(cacheElement.getKey(), cacheElement);
    }

    @Override
    public <K, V> V get(K key) {
        return (V) elements.get(key);
    }

    @Override
    public int getHitCount() {
        return 0;
    }

    @Override
    public int getMissCount() {
        return 0;
    }
}
