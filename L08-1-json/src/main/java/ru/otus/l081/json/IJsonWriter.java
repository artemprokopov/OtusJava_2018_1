package ru.otus.l081.json;

/**
 * Интерфейс серилизиции объекта в JSON.
 * @author Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
public interface IJsonWriter {
    /**
     * Метод серелизует переданный объект в JSON.
     * @param writeObject серелизуемый объект.
     */
    void write(Object writeObject);
}
