package taskjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection implements MyConnection {
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;


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

    @Override
    public void putConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
