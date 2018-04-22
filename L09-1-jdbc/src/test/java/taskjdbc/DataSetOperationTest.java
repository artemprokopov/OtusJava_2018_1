package taskjdbc;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataSetOperationTest {
    private final String mySqlUrl = "jdbc:mysql://localhost:3306/otus" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
    private final String user = "otus";
    private final String password = "OtusJdbc$12345";
    private final String table = "myORM";
    private final MySqlConnection mySqlConnection = new MySqlConnection(mySqlUrl, user, password);

    private final LoadDataSetFromBaseCommand<UserDataSet> loadDataSetFromBaseCommand
            = new LoadDataSetFromBaseCommand<>(mySqlConnection.getConnection(), table);
    private final SaveDataSetToBaseCommand<UserDataSet> saveDataSetToBaseCommand
            = new SaveDataSetToBaseCommand<>(mySqlConnection.getConnection(), table);

    private final long userID = System.currentTimeMillis();


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