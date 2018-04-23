package taskjdbc;

import javax.naming.OperationNotSupportedException;

/**
 * Интрефейс комнд выполняющих операции по манипуляции над объектами.
 * @author Artem Prokopov
 * @since 20/04/2018
 * @version 1.0
 * @param <T> параметр типа объекта над которыми совершают операции методы объекта, наследники {@link DataSet}.
 */
public interface Command<T extends DataSet> {
    /**
     * Метод производит операции над передаваемым объектом.
     * @param user объект типа {@literal <T>}.
     * @throws OperationNotSupportedException выбрасывается если метод не пределен в классе реализующим данный интерфейс.
     */
    default void execute(T user) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    /**
     * Метод производит объект типа {@literal <T>}, на основании переданного уникального идентификационного номера id
     * и типа определяемого классом наследником от типа {@literal <T>}.
     * @param id уникальный идентификационный номер объекта.
     * @param clazz класс генерируемого объекта, параметризируемого типом {@literal <T>}.
     * @return объект типа {@literal <T>} наследник абстрактого класса {@link DataSet}.
     * @throws OperationNotSupportedException выбрасывается если метод не пределен в классе реализующим данный интерфейс.
     */
    default T execute(long id, Class<T> clazz) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
