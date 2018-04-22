package taskjdbc;

import javax.naming.OperationNotSupportedException;

public class DataSetOperation<T extends DataSet> {
    private Command<T> saveObjectCommand;
    private Command<T> loadObjectCommand;

    public DataSetOperation(Command<T> initSaveObjectCommand, Command<T> initLoadObjectCommand) {
        assert (initSaveObjectCommand != null);
        assert (initLoadObjectCommand != null);
        this.saveObjectCommand = initSaveObjectCommand;
        this.loadObjectCommand = initLoadObjectCommand;
    }

    public void saveObject(T user) {
        try {
            saveObjectCommand.execute(user);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public T loadObject(long id, Class<T> clazz) {
        try {
            return loadObjectCommand.execute(id, clazz);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
