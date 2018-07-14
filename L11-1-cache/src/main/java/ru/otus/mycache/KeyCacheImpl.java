package ru.otus.mycache;

import java.util.Objects;
/**
 * Класс ключа элемента в кэше.
 * @param <K> тип ключа.
 * @author Artem Prokopov
 * @since 14/07/2018
 * @version 1.0
 */
public class KeyCacheImpl<K> implements KeyCache<K> {
    /**
     * Значение ключа.
     */
    private K key;

    /**
     * Конструктор.
     * @param key значеник ключа.
     */
    public KeyCacheImpl(K key) {
        this.key = key;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KeyCacheImpl<?> keyCache = (KeyCacheImpl<?>) o;
        return Objects.equals(key, keyCache.key);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key);
    }
}
