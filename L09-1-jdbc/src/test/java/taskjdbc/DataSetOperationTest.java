package taskjdbc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Тест для класса {@link DataSetOperation}.
 * @author Artem Prokopov
 * @since 20/04/2018
 * @version 1.0
 */
public class DataSetOperationTest {
    /**
     * Поле URL к базе.
     */
    private final String mySqlUrl = "jdbc:mysql://localhost:3306/otus"
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
    /**
     * Поле имени пользователя к тестовой базе.
     */
    private final String user = "otus";
    /**
     * Поле пароля к тестовой базе.
     */
    private final String password = "12345";
    /**
     * Поле имени тестовой таблицы.
     */
    private final String table = "myORM";
    /**
     * Создаем поле типом {@link MySqlConnection} который обеспечивает предоставления соединения
     * {@link java.sql.Connection} к тестовой базе данных.
     */
    private final MySqlConnection mySqlConnection = new MySqlConnection(mySqlUrl, user, password);
    /**
     * Создаем объект команды получения объекта из базы данных {@link LoadDataSetFromBaseCommand}.
     */
    private final LoadDataSetFromBaseCommand<UserDataSet> loadDataSetFromBaseCommand
            = new LoadDataSetFromBaseCommand<>(mySqlConnection.getConnection(), table);
    /**
     * Создаем объект команды сохранения объекта в базе данных {@link SaveDataSetToBaseCommand}.
     */
    private final SaveDataSetToBaseCommand<UserDataSet> saveDataSetToBaseCommand
            = new SaveDataSetToBaseCommand<>(mySqlConnection.getConnection(), table);
    /**
     * Генерируем id для тестового пользователя.
     */
    private final long userID = System.currentTimeMillis();
    /**
     * Тестовый метод, тестируем методы загрузки и выгрузки отображения объекта в базу данных
     * {@link DataSetOperation#saveObject(DataSet)} и {@link DataSetOperation#loadObject(long, Class)}.
     */
    @Test
    public void testSaveLoadObject() {
        DataSetOperation<UserDataSet> dataSetOperation
                = new DataSetOperation<>(saveDataSetToBaseCommand, loadDataSetFromBaseCommand);
        UserDataSet userDataSetTestObject = new UserDataSet(this.userID, "Anton", 35);
        dataSetOperation.saveObject(userDataSetTestObject);
        UserDataSet userDataSetResult = dataSetOperation.loadObject(userID, UserDataSet.class);
        assertEquals(userDataSetResult, userDataSetTestObject);
    }
}