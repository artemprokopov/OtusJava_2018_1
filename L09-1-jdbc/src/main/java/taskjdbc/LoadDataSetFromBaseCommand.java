package taskjdbc;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadDataSetFromBaseCommand<T extends DataSet> implements Command<T> {
    private final Connection connection;
    private final String table;
    private final String select;

    public LoadDataSetFromBaseCommand(Connection connection, String initTable) {
        assert (connection != null);
        assert (initTable != null);
        this.connection = connection;
        this.table = initTable;
        this.select = String.format("SELECT * FROM %s WHERE id=?", initTable);
    }

    public T execute(long idUser, Class<T> clazz) {
        T result = null;
        try {
            if (connection.isClosed()) {
                throw new SQLException(this + " method execute(long, Class<DataSet>)!" + " Connection isClosed!");
            }
            connection.setAutoCommit(false);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        try (PreparedStatement  preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               long idQuery = resultSet.getLong("id");
                assert (idUser == idQuery);
                String nameQuery = resultSet.getString("name");
                int ageQuery = resultSet.getInt("age");
                Constructor clazzConstructor = null;
                try {
                   clazzConstructor = clazz.getConstructor(long.class, String.class, int.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                if (clazzConstructor != null) {
                    try {
                        result = (T) clazzConstructor.newInstance(idQuery, nameQuery, ageQuery);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            connection.setAutoCommit(true);
            resultSet.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }
}
