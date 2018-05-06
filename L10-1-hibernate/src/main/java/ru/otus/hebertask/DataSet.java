package ru.otus.hebertask;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * Класс данных пользователяб предоставляет уникальный идентификационный номер id.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
@MappedSuperclass
public class DataSet {
    /**
     * Уникальный идентификационный номер.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
