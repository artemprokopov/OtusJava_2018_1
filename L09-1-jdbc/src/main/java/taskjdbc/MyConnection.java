package taskjdbc;

import java.sql.Connection;
/**
 * Интрефейс предоставляющий методы получения соединения с базой данных типа {@link Connection} и
 * освобождающее данное соединение.
 * @author Artem Prokopov
 * @since 20/04/2018
 * @version 1.0
 */
public interface MyConnection {
    /**
     * Метод предоставляет соединение с базой данных {@link Connection}.
     * @return соединение с базой данных типа {@link Connection}
     */
    Connection getConnection();

    /**
     * Освобождает соединение сбаззой данных типа {@link Connection}.
     */
    void putConnection();
}
