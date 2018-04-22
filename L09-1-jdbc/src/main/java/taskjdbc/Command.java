package taskjdbc;

import javax.naming.OperationNotSupportedException;

public interface Command<T extends DataSet> {
    /**
     *
     * @param user
     * @throws OperationNotSupportedException
     */
    default void execute(T user) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    /**
     *
     * @param id
     * @param clazz
     * @return
     * @throws OperationNotSupportedException
     */
    default T execute(long id, Class<T> clazz) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
