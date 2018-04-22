package taskjdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveDataSetToBaseCommand<T extends DataSet> implements Command<T> {
    private final Connection connection;
    private final String tableBase;
    private final String insert;

    public SaveDataSetToBaseCommand(Connection connection, String initTable) {
        assert (connection != null);
        assert (connection != null);
        this.connection = connection;
        this.tableBase = initTable;
        this.insert = String.format("INSERT INTO %s(id, name, age) VALUES (? , ?, ?)", initTable);
    }

    public void execute(T user) {
        assert (user != null);
        try {
           if (connection.isClosed()) {
               throw new SQLException(this + " method execute(DataSet)!" + " Connection isClosed!");
           }
            connection.setAutoCommit(false);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            long idUser = user.getId();
            String nameUser = null;
            int ageUser = 0;
            Class clazz = user.getClass();
            Method getName = null;
            Method getAge = null;
            try {
                getName = clazz.getMethod("getName");
                getAge = clazz.getMethod("getAge");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (getName != null) {
                try {
                    nameUser = (String) getName.invoke(user);
                } catch (IllegalAccessException  | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (getAge != null) {
                try {
                    ageUser = (int) getAge.invoke(user);
                } catch (IllegalAccessException  | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            preparedStatement.setLong(1, idUser);
            preparedStatement.setString(2, nameUser);
            preparedStatement.setInt(3, ageUser);
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
