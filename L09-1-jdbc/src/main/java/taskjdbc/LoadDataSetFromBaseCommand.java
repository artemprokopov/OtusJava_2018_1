package taskjdbc;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Класс реализует операцию загрузки из базы данных объекта содержащих данные пользователя.
 * @author Artem Prokopov
 * @param <T> объект наследник {@link DataSet}
 * @since 20/04/2018
 * @version 1.0
 */

public class LoadDataSetFromBaseCommand<T extends DataSet> implements Command<T> {
    /**
     * Поле соединения с базой данных.
     */
    private final Connection connection;
    /**
     * Запрос на выборку данных.
     */
    private final String select;

    /**
     * Конструктор.
     * @param connection соединение с базой данных типа {@link Connection}
     * @param initTable имя таблицы в базе данных, где хранятся данный объекта.
     */
    public LoadDataSetFromBaseCommand(Connection connection, String initTable) {
        assert (connection != null);
        assert (initTable != null);
        this.connection = connection;
        this.select = String.format("SELECT * FROM %s WHERE id=?", initTable);
    }

    /**
     * Метод запускает процесс загрузки объекта по его уникальному идентификационному номеру и типу класса.
     * @param idUser уникальный идентификационный номер объекта который требуется.
     * @param clazz класс генерируемого объекта, параметризируемого типом {@literal <T>}.
     * @return объект типа {@literal <T>} наследник {@link DataSet}.
     */
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
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long idQuery = resultSet.getLong("id");
                    assert (idUser == idQuery);
                    String nameQuery = resultSet.getString("name");
                    int ageQuery = resultSet.getInt("age");
                    Constructor<T> clazzConstructor = null;
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
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
