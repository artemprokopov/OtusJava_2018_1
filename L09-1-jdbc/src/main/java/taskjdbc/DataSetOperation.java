package taskjdbc;

import javax.naming.OperationNotSupportedException;
/**
 * Класс реализует операции над объектами содержащих данные пользователя.
 * @author Artem Prokopov
 * @param <T> объект наследник {@link DataSet}
 * @since 20/04/2018
 * @version 1.0
 */

public class DataSetOperation<T extends DataSet> {
    /**
     * Поле инициализируется конструктором, хранит ссылку на объект
     * выполняющийоперацию по сохранению объекта {@literal <T>}.
     */
    private Command<T> saveObjectCommand;
    /**
     * Поле инициализируется конструктором, хранит ссылку на объект
     * выполняющийоперацию по загрузки объекта {@literal <T>} из места хранения.
     */
    private Command<T> loadObjectCommand;

    /**
     * Конструктор.
     * @param initSaveObjectCommand объект выполняющийоперацию по сохранение объекта {@literal <T>} в место хранения
     *      *                              реализует интерфейс {@link Command<T>}
     * @param initLoadObjectCommand объект выполняющийоперацию по загрузки объекта {@literal <T>} из места хранения
     *                              реализует интерфейс {@link Command<T>}
     */
    public DataSetOperation(Command<T> initSaveObjectCommand, Command<T> initLoadObjectCommand) {
        assert (initSaveObjectCommand != null);
        assert (initLoadObjectCommand != null);
        this.saveObjectCommand = initSaveObjectCommand;
        this.loadObjectCommand = initLoadObjectCommand;
    }

    /**
     * Метод реализующий операцию созранению объекта в хранилище.
     * @param user сохраняемый объект.
     */
    public void saveObject(T user) {
        try {
            saveObjectCommand.execute(user);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод реализует загрузку объекта из хранилища.
     * @param id уникальный идентификационный номер объекта.
     * @param clazz тип объекта.
     * @return объект наследник типа {@link DataSet}.
     */
    public T loadObject(long id, Class<T> clazz) {
        try {
            return loadObjectCommand.execute(id, clazz);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
