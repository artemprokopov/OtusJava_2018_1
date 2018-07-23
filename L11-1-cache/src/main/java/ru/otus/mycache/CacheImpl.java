package ru.otus.mycache;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheImpl<K, V> implements ICache<K, V> {
    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;

    private int hitCount = 0;
    private int  missCount = 0;

    private final Map<KeyCache<K>,CacheElement<K, V>> elements = new WeakHashMap<>();

    public CacheImpl(int maxElements, long lifeTimeMs, long idleTimeMs) {
        if (maxElements > 0 ) {
            this.maxElements = maxElements;
        } else {
            this.maxElements = 0;
        }

        if (lifeTimeMs > 0) {
            this.lifeTimeMs = lifeTimeMs;
        } else {
            this.lifeTimeMs = 0;
        }

        if (idleTimeMs > 0) {
            this.idleTimeMs = idleTimeMs;
        } else {
            this.idleTimeMs = 0;
        }
    }

    @Override
    public void put(K key, V value) {
        if (maxElements > 0 && elements.size() < maxElements ) {
            CacheElement<K, V> cacheElement = new CacheElementImpl<>(key, value, idleTimeMs, lifeTimeMs);
            elements.put(cacheElement.getKey(), cacheElement);
        } else if (maxElements == 0){
            CacheElement<K, V> cacheElement = new CacheElementImpl<>(key, value, idleTimeMs, lifeTimeMs);
            elements.put(cacheElement.getKey(), cacheElement);
        }
    }

    @Override
    public  V get(K key) {
        KeyCache<K> keyCache = new KeyCacheImpl<>(key);
        CacheElement<K, V> tempElement = elements.get(keyCache);
        if (tempElement != null) {
            hitCount++;
        } else {
            missCount++;
            return null;
        }
         V resultCacheElement = tempElement.getValue();
        if (resultCacheElement == null) {
            return null;
        }
        return  resultCacheElement;
    }

    @Override
    public int getHitCount() {
        return hitCount;
    }

    @Override
    public int getMissCount() {
        return missCount;
    }
}
