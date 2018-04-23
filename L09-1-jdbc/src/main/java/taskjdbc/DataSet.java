package taskjdbc;

import java.util.Objects;

/**
 * Абстрактный класс данных пользователяб предоставляет уникальный идентификационный номер id.
 * @author Artem Prokopov
 * @since 20/04/2018
 * @version 1.0
 */
public abstract class DataSet {
    /**
     * Уникальный идентификационный номер.
     */
    private final long id;

    /**
     * Конструктор.
     * @param id принимает уникальный идентификацинный ключ пользователя.
     */
    public DataSet(long id) {
        this.id = id;
    }

    /**
     * Метод возвращает уникальный идентификационный ключ пользователя.
     * @return уникальный идентификационный ключ пользователя.
     */
    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataSet dataSet = (DataSet) o;
        return id == dataSet.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}