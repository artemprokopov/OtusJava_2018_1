package taskjdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Класс реализует операцию сохранение  в базе данных объекта содержащих данные пользователя.
 * @author Artem Prokopov
 * @param <T> объект наследник {@link DataSet}
 * @since 20/04/2018
 * @version 1.0
 */

public class SaveDataSetToBaseCommand<T extends DataSet> implements Command<T> {
    /**
     * Поле соединения с базой данных.
     */
    private final Connection connection;
    /**
     * Запрос на вставку данных в БД.
     */
    private final String insert;
    /**
     * Конструктор.
     * @param connection соединени сбазой данных типа {@link Connection}
     * @param initTable имя таблицы в базе данных, где хранятся данный объекта.
     */
    public SaveDataSetToBaseCommand(Connection connection, String initTable) {
        assert (connection != null);
        assert (connection != null);
        this.connection = connection;
        this.insert = String.format("INSERT INTO %s(id, name, age) VALUES (? , ?, ?)", initTable);
    }

    /**
     * Метод запускает процесс сохранение объекта в БД.
     * @param user объект типа {@literal <T>}б который сохраняется в БД.
     */
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
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
