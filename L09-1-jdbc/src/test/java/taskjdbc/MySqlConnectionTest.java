package taskjdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MySqlConnectionTest {
    private final String mySqlUrl = "jdbc:mysql://localhost:3306/otus" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
    private final String user = "otus";
    private final String password = "OtusJdbc$12345";

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