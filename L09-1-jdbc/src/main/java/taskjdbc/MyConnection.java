package taskjdbc;

import java.sql.Connection;

public interface MyConnection {
    Connection getConnection();
    void putConnection();
}
