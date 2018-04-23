package taskjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Класс предоставляющий методы получения соединения с базой данных типа {@link Connection} и
 * освобождающее данное соединение реализует интерфейс {@link MyConnection}.
 * @author Artem Prokopov
 * @since 20/04/2018
 * @version 1.0
 */
public class MySqlConnection implements MyConnection {
    /**
     * URL соединеня с базой данных.
     */
    private final String url;
    /**
     * Логин.
     */
    private final String user;
    /**
     * Пароль доступа к базе данных.
     */
    private final String password;
    /**
     * Поле хранит соединени с базой данных.
     */
    private Connection connection;

    /**
     * Конструктор.
     * @param initUrl URL соединения с базой данных.
     * @param initUser логин.
     * @param initPassword рароль доступа к дбазе данных.
     */
    public MySqlConnection(String initUrl, String initUser, String initPassword) {
        this.url = initUrl;
        this.user = initUser;
        this.password = initPassword;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * Метод возвращающий соединение к базе данных типа{@link Connection}.
     * @return соединение кбазе данных.
     */
    @Override
    public Connection getConnection() {
        if (connection == null) {
            try {
                this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Освобождает соединение к текущей базе данных.
     */
    @Override
    public void putConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
