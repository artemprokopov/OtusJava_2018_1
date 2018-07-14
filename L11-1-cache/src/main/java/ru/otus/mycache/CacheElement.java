package ru.otus.mycache;

import java.lang.ref.SoftReference;

/**
 * Интерфейс хранимого элемента в кэше.
 * @param <K> тип ключа, реализует интерфейс {@link KeyCache}.
 * @param <V> тип значения.
 * @author Artem Prokopov
 * @since 14/07/2018
 * @version 1.0
 */
public interface CacheElement<K, V> {
    /**
     * Метод получения ключа хранимого в кэше объекта.
     * @return ключ хранимоого в кэше объекта.
     */
    KeyCache<K> getKey();

    /**
     * Метод получения значения хранимого в кэше объекта.
     * @return значение хранимого в
     */
    V getValue();
}
