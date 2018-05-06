package ru.otus.hebertask;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Класс отсутствия данных.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
@Entity
public class EmtyDataSet {
    /**
     * Поле уникального идентификационного номера пользователя.
     */
    @Id
    private long id;
}
