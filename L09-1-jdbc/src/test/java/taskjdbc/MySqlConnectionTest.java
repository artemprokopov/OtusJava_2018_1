package taskjdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Тест для класса {@link MySqlConnection}.
 * @author Artem Prokopov
 * @since 20/04/2018
 * @version 1.0
 */
public class MySqlConnectionTest {
    /**
     * Поле URL к базе.
     */
    private final String mySqlUrl = "jdbc:mysql://127.0.0.1:3306/otus"
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC&useSSL=true&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    /**
     * Поле имени пользователя к тестовой базе.
     */
    private final String user = "otus";
    /**
     * Поле пароля к тестовой базе.
     */
    private final String password = "12345";

    /**
     * Тестируем методы класса {@link MySqlConnection#getConnection()} и {@link MySqlConnection#putConnection()}.
     */
    @Test
    public void testGetAndPutConnection() {
        MySqlConnection mySqlConnection = new MySqlConnection(mySqlUrl, user, password);
        Connection connection = mySqlConnection.getConnection();
        try {
            assertFalse(connection.isClosed());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        try {
            assertTrue(connection.isValid(1));
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        mySqlConnection.putConnection();
        try {
            assertFalse(connection.isValid(1));
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        try {
            assertTrue(connection.isClosed());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}