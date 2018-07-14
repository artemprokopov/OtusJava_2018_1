package ru.otus.mycache;
/**
 * Интерфейс ключа элемента в кэше.
 * @author Artem Prokopov
 * @since 14/07/2018
 * @version 1.0
 */

public interface KeyCache<K> {
    /**
     * Метод возвращает хранящийся в объекте значение ключа.
     * @return значение ключа.
     */
    K getKey();

}
