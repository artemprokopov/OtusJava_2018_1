package ru.otus.hebertask;
/**
 * Класс данных пользователяб предоставляет уникальный идентификационный номер id.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
public class DataSet {
    /**
     * Уникальный идентификационный номер.
     */
    private long id;

    /**
     * Возвращает значение уникального идентификационного номера.
     * @return значение поля {@link DataSet#id}
     */
    public long getId() {
        return id;
    }

    /**
     * Устанавливает значение уникального идентификационного номера.
     * @param id новое значение уникального идентификаионного номера.
     */
    public void setId(long id) {
        this.id = id;
    }
}
