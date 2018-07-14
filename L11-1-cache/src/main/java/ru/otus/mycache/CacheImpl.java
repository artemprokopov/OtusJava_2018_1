package ru.otus.mycache;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheImpl<K, V> implements ICache<K, V> {
    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<KeyCache<K>, SoftReference<CacheElement<K, V>>> elements = new WeakHashMap<>();
    @Override
    public void put(K key, V value) {
        CacheElement<K, V> cacheElement = new CacheElementImpl<>(key, value, idleTimeMs, lifeTimeMs);
        elements.put(cacheElement.getKey(), new SoftReference<>(cacheElement));
    }

    @Override
    public  V get(K key) {
        return  elements.get(key).get().getValue();
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
