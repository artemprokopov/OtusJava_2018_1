package ru.otus.mycache;
/**
 * Интерфейс операций с  кэшем.
 * @author Artem Prokopov
 * @since 14/07/2018
 * @version 1.0
 */
public interface ICache {
    /**
     * Метод сохраняет значение value по ключу key в кэше.
     * @param key ключ
     * @param value значение
     * @param <K> тип ключа.
     * @param <V> тип значения.
     */
    <K, V> void put(K key, V value);

    /**
     * Метод возвращает значение из кэша по ключу.
     * @param key ключ.
     * @param <K> тип ключа.
     * @param <V> тип значения.
     * @return значение сохраненное к кэше илбо null.
     */
    <K, V> V get(K key);

    /**
     * Возвращает количество успешных попаданий в кэш.
     * @return количество попаданий в кэш.
     */
    int getHitCount();

    /**
     * Количество промахов по кэшу.
     * @return количество промахов.
     */
    int getMissCount();

}
